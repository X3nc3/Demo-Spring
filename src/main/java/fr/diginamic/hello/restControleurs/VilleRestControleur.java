package fr.diginamic.hello.restControleurs;

import fr.diginamic.hello.entity.Ville;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleRestControleur {

    @GetMapping
    public List<Ville> listeVilles(){
        ArrayList<Ville> listeVille = new ArrayList<>();
        listeVille.add(new Ville("Nice", 343_000));
        listeVille.add(new Ville("Carcassonne", 47_800));
        listeVille.add(new Ville("Lyon", 484_000));
        listeVille.add(new Ville("Foix", 9_700));
        listeVille.add(new Ville("Pau", 77_200));
        listeVille.add(new Ville("Marseille", 850_700));
        listeVille.add(new Ville("Tarbes", 40_600));
        return listeVille;
    }


}
