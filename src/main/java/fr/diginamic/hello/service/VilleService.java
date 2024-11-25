package fr.diginamic.hello.service;

import fr.diginamic.hello.entity.Ville;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VilleService {

    ArrayList<Ville> listeVille = new ArrayList<> (Arrays.asList(new Ville("Nice", 343_000), new Ville("Carcassonne", 47_800), new Ville("Lyon", 484_000)));

    public List<Ville> getVilles() {
        return listeVille;
    }

    public void postVille(Ville nvVille) {
        listeVille.add(nvVille);
    }

    public Ville getVilleById(int id) {
        return listeVille.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public void putVille(int id, Ville nvVille) {
        Ville v = getVilleById(id);
        v.setNbHabitants(nvVille.getNbHabitants());
        v.setNom(nvVille.getNom());
    }

    public void deleteVilleById(int id) {
        Ville v = getVilleById(id);
        listeVille.remove(v);
    }
}
