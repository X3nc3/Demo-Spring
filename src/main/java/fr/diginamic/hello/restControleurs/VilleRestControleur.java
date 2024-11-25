package fr.diginamic.hello.restControleurs;

import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleRestControleur {

    @Autowired
    VilleService villeService;

    @GetMapping
    public List<Ville> listeVilles(){
        return villeService.getVilles();
    }

    @PostMapping
    public ResponseEntity<String> insertVille(@RequestBody Ville nvVille){
        for (Ville ville : villeService.getVilles()) {
            if (ville.getId() == nvVille.getId()) {
                return ResponseEntity.badRequest().body("La ville existe déjà");
            }
        }

        villeService.postVille(nvVille);
        return ResponseEntity.ok("Ville insérée avec succès");
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Ville> afficherVilles(@PathVariable int id){
        for (Ville ville : villeService.getVilles()) {
            if (ville.getId() == id) {
                return ResponseEntity.ok(villeService.getVilleById(ville.getId()));
            }
        }

        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping(path = {"{id}"})
    public ResponseEntity<String> modifierVille(@PathVariable int id, @RequestBody Ville ville){
        for (Ville v : villeService.getVilles()) {
            if (v.getId() == id) {
                villeService.putVille(id, ville);
                return ResponseEntity.ok("La ville " + v.getNom() + "à etait modifiée");
            }
        }

        return ResponseEntity.badRequest().body("L'id' n'existe pas");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id){
        for (Ville v : villeService.getVilles()) {
            if (v.getId() == id) {
                villeService.deleteVilleById(v.getId());
                return ResponseEntity.ok("La ville " + v.getNom() + " est supprimée");
            }
        }

        return ResponseEntity.badRequest().body("L'id' n'existe pas");
    }

}
