package fr.diginamic.hello.restControleurs;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.exception.Controle;
import fr.diginamic.hello.mapper.VilleMapper;
import fr.diginamic.hello.service.VilleService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleRestControleur {

    @Autowired
    VilleService villeService;

    @Autowired
    VilleMapper villeMapper;

    @GetMapping
    public List<VilleDto> getVilles(){
        Iterable<Ville> villes = villeService.getAllVilles();
        List<VilleDto> villeDtos = new ArrayList<>();
        for(Ville ville : villes){
            villeDtos.add(villeMapper.toDto(ville));
        }
        return villeDtos;
    }

    @GetMapping(path = "{id}")
    public VilleDto getVille(@PathVariable int id) throws Controle {
            return villeMapper.toDto(villeService.getVille(id));}

    @GetMapping(path = "nom/{nom}")
    public VilleDto getVille(@PathVariable String nom) throws Controle {return villeMapper.toDto(villeService.getVilleByNom(nom));}

    @PostMapping
    public ResponseEntity<String> postVille(@RequestBody VilleDto nvVille) throws Controle {
        villeService.addVille(villeMapper.toEntity(nvVille));
        return ResponseEntity.ok("Ville insérée avec succès");
    }

    @PutMapping
    public ResponseEntity<String> putVille(@RequestBody VilleDto nvville) throws Controle {
            villeService.updateVille(villeMapper.toEntity(nvville));
            return ResponseEntity.ok("La ville " + nvville.getNom() + " à etait modifiée");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id) throws Controle {
        villeService.deleteVille(id);
        return ResponseEntity.ok("La ville avec l'id : " + id + " est supprimée");
    }

    @GetMapping("/findByDepartementCodeOrderByNbHabitantsDesc/{codeDep}/{n}")
    public List<Ville> findByDepartementCodeOrderByNbHabitantsDesc(@PathVariable String codeDep, @PathVariable Integer n) throws Controle {
        return villeService.findByDepartementCodeOrderByNbHabitantsDesc(codeDep,n);
    }

    @GetMapping("/findByNomStartingWithIgnoreCase/{nom}")
    public List<Ville> findByNomStartingWithIgnoreCase(@PathVariable String nom) throws Controle {
        return villeService.findByNomStartingWithIgnoreCase(nom);
    }

    @GetMapping("/findByNbHabitantsGreaterThan/{min}")
    public List<Ville> findByNbHabitantsGreaterThan(@PathVariable Integer min) throws Controle {
        return villeService.findByNbHabitantsGreaterThan(min);
    }

    @GetMapping("/findByNbInhabitantsBetween/{min}/{max}")
    public List<Ville> findByNbInhabitantsBetween(@PathVariable Integer min,@PathVariable Integer max) throws Controle {
        return villeService.findByNbInhabitantsBetween(min,max);
    }

    @GetMapping("/findByDepartementCodeAndNbHabitantsGreaterThan/{code}/{min}")
    public List<Ville> findByDepartementCodeAndNbHabitantsGreaterThan(@PathVariable String code,@PathVariable Integer min) throws Controle {
        return villeService.findByDepartementCodeAndNbHabitantsGreaterThan(code, min);
    }

    @GetMapping("/findByDepartementCodeAndNbHabitantsBetween/{code}/{min}/{max}")
    public List<Ville> findByDepartementCodeAndNbHabitantsBetween(@PathVariable String code,@PathVariable Integer min,@PathVariable Integer max) throws Controle {
        return villeService.findByDepartementCodeAndNbHabitantsBetween(code,min,max);
    }

    @GetMapping("/{min}/ficheCsv")
    public void ficheVilles(@PathVariable Integer min, HttpServletResponse response) throws Controle, IOException, DocumentException {
        response.setHeader("Content-Disposition", "attachment; filename=\"villes.csv\"");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));

        writer.write("nom; nombre habitants; code département; nom département\n");
        List<Ville> villes = findByNbHabitantsGreaterThan(min);
        for (Ville ville : villes) {
            writer.write(ville.getNom() + "; " + ville.getNbHabitants() + "; " + ville.getDepartement().getCode() + "; " + String.join(";", ville.getDepartement().getNom()) + "\n");
        }

        response.flushBuffer();
    }
}
