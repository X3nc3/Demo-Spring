package fr.diginamic.hello.service;

import fr.diginamic.hello.dao.DepartementDao;
import fr.diginamic.hello.entity.Departement;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DepartementService {
    @Autowired
    DepartementDao departementDao;

    @PostConstruct
    public void init() {
        departementDao.create(new Departement("Alpes-Maritimes","06"));
        departementDao.create(new Departement("Rhône","69"));
        departementDao.create(new Departement("Ariège", "09"));
        departementDao.create(new Departement("Pyrénées-Atlantiques","64"));
        departementDao.create(new Departement("Bouches-du-Rhône", "13"));
        departementDao.create(new Departement("Aude", "11"));
    }

    public Boolean create(Departement departement) {
        try {
            departementDao.create(departement);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public List<Departement> findAll() {return departementDao.findAll();}

    public Departement findByCode(String code) {return departementDao.findBtCode(code);}

    public Departement findById(int id) {return departementDao.findById(id);}

    public Boolean update(Departement departement) {
        if (departementDao.findById(departement.getId()) == null) {
            return false;
        }else {
            departementDao.update(departement);
            return true;
        }
    }

    public Boolean delete(int id) {
        if (departementDao.findById(id) == null) {
            return false;
        }else {
            departementDao.deleteById(id);
            return true;
        }
    }

}
