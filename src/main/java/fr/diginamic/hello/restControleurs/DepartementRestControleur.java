package fr.diginamic.hello.restControleurs;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.exception.Controle;
import fr.diginamic.hello.mapper.DepartementMapper;
import fr.diginamic.hello.service.DepartementService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementRestControleur {

    @Autowired
    DepartementService departementService;

    @Autowired
    DepartementMapper departementMapper;

    @GetMapping
    public List<DepartementDto> getDepartements() {
        Iterable<Departement> departements = departementService.findAll();
        List<DepartementDto> departementDtos = new ArrayList<>();
        for (Departement departement : departements) {
            departementDtos.add(departementMapper.toDto(departement));
        }
        return departementDtos;
    }

    @GetMapping("{id}")
    public DepartementDto getDepartementById(@PathVariable int id) throws Controle {
        return departementMapper.toDto(departementService.findById(id));
    }

    @GetMapping("/{code}")
    public DepartementDto getDepartemenByCode(@PathVariable String code) throws Controle {
        return departementMapper.toDto(departementService.findByCode(code));
    }

    @PostMapping
    public ResponseEntity<String> postDepartement(@RequestBody DepartementDto departementDto) throws Controle {
        departementService.create(departementMapper.toEntity(departementDto));
        return ResponseEntity.ok("Département crée avec succès");
    }

    @PutMapping
    public ResponseEntity<String> putDepartement(@RequestBody DepartementDto departementDto) throws Controle {
        departementService.update(departementMapper.toEntity(departementDto));
        return ResponseEntity.ok().body("Le département " + departementDto.getNom() + " à etait modifiée");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteDepartement(@PathVariable int id) throws Controle {
        departementService.delete(id);
        return ResponseEntity.ok().body("La ville avec l'id : " + id + " est supprimée");
    }

    @GetMapping("/{code}/fichePdf")
    public void ficheDepartement(@PathVariable String code, HttpServletResponse response) throws Controle, IOException, DocumentException {
        response.setHeader("Content-Disposition", "attachment; filename=\"departements.pdf\"");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.addTitle(departementService.findByCode(code).getNom());
        document.newPage();
        Phrase p1 = new Phrase(code);
        document.add(p1);
        Phrase p2 = new Phrase(departementService.findByCode(code).getVilles().toString());
        document.add(p2);
        document.close();

        response.flushBuffer();
    }
}
