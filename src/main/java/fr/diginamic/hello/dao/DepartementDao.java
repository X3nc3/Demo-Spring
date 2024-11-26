package fr.diginamic.hello.dao;

import fr.diginamic.hello.entity.Departement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void create(Departement departement) {em.persist(departement);}

    public List<Departement> findAll() {
        TypedQuery<Departement> query = em.createQuery("select d from Departement d", Departement.class);
        return query.getResultList();
    }

    public Departement findBtCode(String code) {
        TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d WHERE d.code =\""+code+"\"", Departement.class);
        return query.getSingleResult();
    }

    public Departement findById(int id) {return em.find(Departement.class, id);}

    @Transactional
    public Departement update(Departement departement) {return em.merge(departement);}

    @Transactional
    public void deleteById(int id) {
        Departement departement = findById(id);
        delete(departement);
    }

    @Transactional
    public void delete(Departement departement) {em.remove(departement);}
}
