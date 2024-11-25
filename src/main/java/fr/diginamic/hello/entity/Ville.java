package fr.diginamic.hello.entity;

public class Ville {
    private int id;
    private String nom;
    private int nbHabitants;
    private static int count = 1;


    public Ville(String nom, int nbHabitants) {
        this.id = count++;
        this.nom = nom;
        this.nbHabitants = nbHabitants;
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

    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }
}
