package fr.diginamic.hello.service;

import fr.diginamic.hello.entity.Ville;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VilleService {

    public List<Ville> getVilles() {
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
