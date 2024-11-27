package fr.diginamic.hello.service;

import fr.diginamic.hello.Repository.DepartementRepository;
import fr.diginamic.hello.dao.DepartementDao;
import fr.diginamic.hello.entity.Departement;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {

    @Autowired
    DepartementRepository departementRepository;

//    @Autowired
//    DepartementDao departementDao;

//    @PostConstruct
//    public void init() {
//        departementDao.create(new Departement("Alpes-Maritimes","06"));
//        departementDao.create(new Departement("Rhône","69"));
//        departementDao.create(new Departement("Ariège", "09"));
//        departementDao.create(new Departement("Pyrénées-Atlantiques","64"));
//        departementDao.create(new Departement("Bouches-du-Rhône", "13"));
//        departementDao.create(new Departement("Aude", "11"));
//    }

    public Iterable<Departement> findAll() {return departementRepository.findAll();}

    public Departement findByCode(String code) {return departementRepository.findByCode(code);}

    public Departement findById(int id) {return departementRepository.findById(id).get();}

    public Boolean create(Departement departement) {
        try {
            departementRepository.save(departement);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public Boolean update(Departement departement) {
//        if (departementRepository.findById(departement.getId()) == null) {
//            return false;
//        }else {
            departementRepository.save(departement);
            return true;
//        }
    }

    public Boolean delete(int id) {
//        if (departementDao.findById(id) == null) {
//            return false;
//        }else {
            departementRepository.deleteById(id);
            return true;
//        }
    }

}
