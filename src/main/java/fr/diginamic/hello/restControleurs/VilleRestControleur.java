package fr.diginamic.hello.restControleurs;


import fr.diginamic.hello.Repository.VilleRepository;
import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/villes")
public class VilleRestControleur {

    @Autowired
    VilleService villeService;

    @GetMapping
    public Iterable<Ville> getVilles(){
        return villeService.getAllVilles();
    }

    @GetMapping(path = "{id}")
    public Ville getVille(@PathVariable int id){return villeService.getVille(id);}

    @GetMapping(path = "nom/{nom}")
    public Ville getVille(@PathVariable String nom){return villeService.getVilleByNom(nom);}

    @PostMapping
    public ResponseEntity<String> postVille(@RequestBody Ville nvVille){
        if (villeService.addVille(nvVille)) {
            return ResponseEntity.ok("Ville insérée avec succès");
        }else {
            return ResponseEntity.badRequest().body("La ville existe déjà");
        }
    }

    @PutMapping
    public ResponseEntity<String> putVille(@RequestBody Ville nvville){
            if (villeService.updateVille(nvville)) {
                villeService.updateVille(nvville);
                return ResponseEntity.ok("La ville " + nvville.getNom() + " à etait modifiée");
        }
        return ResponseEntity.badRequest().body("L'id' n'existe pas");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id){
        if (villeService.deleteVille(id)) {
            villeService.deleteVille(id);
            return ResponseEntity.ok("La ville avec l'id : " + id + " est supprimée");
        }
        return ResponseEntity.badRequest().body("L'id' n'existe pas");
    }

    @GetMapping("/findByDepartementCodeOrderByNbHabitantsDesc/{codeDep}/{n}")
    public List<Ville> findByDepartementCodeOrderByNbHabitantsDesc(@PathVariable String codeDep, @PathVariable Integer n) {
        return villeService.findByDepartementCodeOrderByNbHabitantsDesc(codeDep,n);
    }

    @GetMapping("/findByNomStartingWithIgnoreCase/{nom}")
    public List<Ville> findByNomStartingWithIgnoreCase(@PathVariable String nom){
        return villeService.findByNomStartingWithIgnoreCase(nom);
    }

    @GetMapping("/findByNbHabitantsGreaterThan/{min}")
    public List<Ville> findByNbHabitantsGreaterThan(@PathVariable Integer min){
        return villeService.findByNbHabitantsGreaterThan(min);
    }

    @GetMapping("/findByNbInhabitantsBetween/{min}/{max}")
    public List<Ville> findByNbInhabitantsBetween(@PathVariable Integer min,@PathVariable Integer max){
        return villeService.findByNbInhabitantsBetween(min,max);
    }

    @GetMapping("/findByDepartementCodeAndNbHabitantsGreaterThan/{code}/{min}")
    public List<Ville> findByDepartementCodeAndNbHabitantsGreaterThan(@PathVariable String code,@PathVariable Integer min){
        return villeService.findByDepartementCodeAndNbHabitantsGreaterThan(code, min);
    }

    @GetMapping("/findByDepartementCodeAndNbHabitantsBetween/{code}/{min}/{max}")
    public List<Ville> findByDepartementCodeAndNbHabitantsBetween(@PathVariable String code,@PathVariable Integer min,@PathVariable Integer max){
        return villeService.findByDepartementCodeAndNbHabitantsBetween(code,min,max);
    }

}
