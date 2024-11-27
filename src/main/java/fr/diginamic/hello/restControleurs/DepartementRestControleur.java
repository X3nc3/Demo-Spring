package fr.diginamic.hello.restControleurs;

import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departements")
public class DepartementRestControleur {

    @Autowired
    DepartementService departementService;

    @GetMapping
    public Iterable<Departement> getDepartements() {
        return departementService.findAll();
    }

    @GetMapping("{id}")
    public Departement getDepartementById(@PathVariable int id) {
            return departementService.findById(id);
    }

    @GetMapping("/{code}")
    public Departement getDepartemenByCode(@PathVariable String code) {
            return departementService.findByCode(code);
    }

    @PostMapping
    public ResponseEntity<String> postDepartement(@RequestBody Departement departement) {
        if (departementService.create(departement)) {
            return ResponseEntity.ok("Département crée avec succès");
        }
        return ResponseEntity.badRequest().body("Le département existe déjà");
    }

    @PutMapping
    public ResponseEntity<String> putDepartement(@RequestBody Departement departement) {
        if (departementService.update(departement)) {
            return ResponseEntity.ok().body("Le département " + departement.getNom() + " à etait modifiée");
        }
        return ResponseEntity.badRequest().body("L'id' n'existe pas");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteDepartement(@PathVariable int id) {
        if (departementService.delete(id)) {
            return ResponseEntity.ok().body("La ville avec l'id : " + id + " est supprimée");
        }
        return ResponseEntity.badRequest().body("L'id' n'existe pas");
    }
}
