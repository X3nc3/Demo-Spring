package fr.diginamic.hello.dao;

import fr.diginamic.hello.entity.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void create(Ville ville) {
        em.persist(ville);
    }

    public List<Ville> findAll() {
        TypedQuery<Ville> query = em.createQuery("SELECT ville FROM Ville ville", Ville.class);
        return query.getResultList();
    }

    public Ville findById(int id) {return em.find(Ville.class, id);}

    public Ville findByName(String name) {
        TypedQuery<Ville> query = em.createQuery("SELECT ville FROM Ville ville WHERE ville.nom =\""+name+"\"", Ville.class);
        return query.getSingleResult();
    }

    @Transactional
    public Ville update(Ville ville) {return em.merge(ville);}

    @Transactional
    public void delete(Ville ville) {em.remove(ville);}

    @Transactional
    public void deleteById(int id) {
        Ville ville = em.find(Ville.class, id);
        em.remove(ville);
    }

    public List<Ville> findByDepartmentCodeOrderByNbInhabitantsDesc(String codeDep, Integer n) {
        TypedQuery<Ville> query = em.createQuery("SELECT ville FROM Ville ville where ville.departement.code=\""+codeDep+"\" order by nbHabitants desc LIMIT "+n,Ville.class);
        return query.getResultList();
    }

    public List<Ville> findByDepartmentCodeAndNbInhabitantsBetween(String codeDep, Integer min, Integer max) {
        TypedQuery<Ville> query = em.createQuery("SELECT ville FROM Ville ville where ville.departement.code=\""+codeDep+"\" and ville.nbHabitants > "+min+" and ville.nbHabitants < "+max,Ville.class);
        return query.getResultList();
    }
}
