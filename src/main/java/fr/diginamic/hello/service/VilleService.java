package fr.diginamic.hello.service;

import fr.diginamic.hello.Repository.DepartementRepository;
import fr.diginamic.hello.Repository.VilleRepository;
import fr.diginamic.hello.dao.VilleDao;
import fr.diginamic.hello.entity.Ville;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class VilleService {

    @Autowired
    VilleRepository villeRepository;

    @Autowired
    DepartementRepository departementRepository;

//    @Autowired
//    VilleDao villeDao;

//    @Autowired
//    DepartementService departementService;
//
//    @PostConstruct
//    public void init() {
//        villeDao.create(new Ville("Nice", 343000, departementService.findByCode("06")));
//        villeDao.create(new Ville("Lyon", 484000, departementService.findByCode("69")));
//        villeDao.create(new Ville("Foix", 9700, departementService.findByCode("09")));
//        villeDao.create(new Ville("Pau", 77200, departementService.findByCode("64")));
//        villeDao.create(new Ville("Marseille", 850700, departementService.findByCode("13")));
//        villeDao.create(new Ville("Carcassonne", 478000, departementService.findByCode("11")));
//    }

    public Iterable<Ville> getAllVilles() {
        Pageable pageable = PageRequest.of(10, 10);
        return villeRepository.findAll(pageable);}

    public Ville getVille(int id) {
        try {
            return villeRepository.findById(id).get();
        }catch(Exception e) {
            return null;
        }
    }

    public Ville getVilleByNom(String nom) {
        try{
            return villeRepository.findByNom(nom);
        }catch (Exception e) {
            return null;
        }
    }

    public boolean addVille(Ville ville) {
        try {
            villeRepository.save(ville);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateVille(Ville ville) {
        try{
            Optional<Ville> oldVille = villeRepository.findById(ville.getId());
            oldVille.get().setNom(ville.getNom());
            oldVille.get().setNbHabitants(ville.getNbHabitants());
            villeRepository.save(oldVille.get());
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    public boolean deleteVille(int id) {
        try {
            villeRepository.deleteById(id);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    public List<Ville> findByDepartementCodeOrderByNbHabitantsDesc(String code, Integer n) {
        Pageable pageable = PageRequest.of(1, n);
        return villeRepository.findByDepartementCodeOrderByNbHabitantsDesc(code, pageable);
    }

    public List<Ville> findByNomStartingWithIgnoreCase(String nom) {
        return villeRepository.findByNomStartingWithIgnoreCase(nom);
    }

    public List<Ville> findByNbHabitantsGreaterThan(Integer min) {
        return villeRepository.findByNbHabitantsGreaterThan(min);
    }

    public List<Ville> findByNbInhabitantsBetween(Integer min, Integer max) {
        return villeRepository.findByNbHabitantsBetween(min, max);
    }

    public List<Ville> findByDepartementCodeAndNbHabitantsGreaterThan(String code, Integer min) {
        return villeRepository.findByDepartementCodeAndNbHabitantsGreaterThan(code,min);
    }

    public List<Ville> findByDepartementCodeAndNbHabitantsBetween(String code, Integer min, Integer max) {
        return villeRepository.findByDepartementCodeAndNbHabitantsBetween(code, min, max);
    }

}
