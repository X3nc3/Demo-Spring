package fr.diginamic.hello.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "CODE")
    private String code;
    @OneToMany(mappedBy = "departement")
    private static List<Ville> villes;

    {
        villes = new ArrayList<>();
    }

    public Departement() {}

    public Departement(String nom, String code) {
        this.nom = nom;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static List<Ville> getVilles() {
        return villes;
    }

    public static void setVilles(List<Ville> villes) {
        Departement.villes = villes;
    }
}
