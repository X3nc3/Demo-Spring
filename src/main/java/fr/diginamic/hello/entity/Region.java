package fr.diginamic.hello.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Region {
    @Id
    private int id;
    @OneToMany(mappedBy = "region")
    private static List<Ville> villes;

    {
        villes = new ArrayList<>();
    }

    public Region() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static List<Ville> getVilles() {
        return villes;
    }

    public static void setVilles(List<Ville> villes) {
        Region.villes = villes;
    }
}
