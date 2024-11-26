package fr.diginamic.hello.restControleurs;


import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleRestControleur {

    @Autowired
    VilleService villeService;

    @GetMapping
    public List<Ville> getVilles(){
        return villeService.getAllVilles();
    }

    @GetMapping(path = "{id}")
    public Ville getVille(@PathVariable int id){return villeService.getVille(id);}

    @GetMapping(path = "nom/{nom}")
    public Ville getVille(@PathVariable String nom){return villeService.getVilleByName(nom);}

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

    @GetMapping("/findByDepartmentCodeOrderByNbInhabitantsDesc/{codeDep}/{n}")
    public List<Ville> findByDepartmentCodeOrderByNbInhabitantsDesc(@PathVariable("codeDep")String codeDep, @PathVariable("n") Integer n) {
        return villeService.findByDepartmentCodeOrderByNbInhabitantsDesc(codeDep,n);
    }

    @GetMapping("/findByDepartmentCodeAndNbInhabitantsBetween/{codeDep}/{min}/{max}")
    public List<Ville> findByDepartmentCodeAndNbInhabitantsBetween(@PathVariable("codeDep")String codeDep, @PathVariable("min") Integer min,@PathVariable("max") Integer max) {
        return villeService.findByDepartmentCodeAndNbInhabitantsBetween(codeDep, min,max);
    }
}
