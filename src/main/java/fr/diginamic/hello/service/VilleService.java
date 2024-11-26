package fr.diginamic.hello.service;

import fr.diginamic.hello.dao.VilleDao;
import fr.diginamic.hello.entity.Ville;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {
    @Autowired
    VilleDao villeDao;

    @Autowired
    DepartementService departementService;

    @PostConstruct
    public void init() {
        villeDao.create(new Ville("Nice", 343000, departementService.findByCode("06")));
        villeDao.create(new Ville("Lyon", 484000, departementService.findByCode("69")));
        villeDao.create(new Ville("Foix", 9700, departementService.findByCode("09")));
        villeDao.create(new Ville("Pau", 77200, departementService.findByCode("64")));
        villeDao.create(new Ville("Marseille", 850700, departementService.findByCode("13")));
        villeDao.create(new Ville("Carcassonne", 478000, departementService.findByCode("11")));
    }

    public List<Ville> getAllVilles() {return villeDao.findAll();}

    public Ville getVille(int id) {
        try {
            return villeDao.findById(id);
        }catch(Exception e) {
            return null;
        }
    }

    public Ville getVilleByName(String name) {
        try{
            return villeDao.findByName(name);
        }catch (Exception e) {
            return null;
        }
    }

    public boolean addVille(Ville ville) {
        try {
            villeDao.create(ville);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    public boolean updateVille(Ville ville) {
        try{
            Ville oldVille = villeDao.findById(ville.getId());
            oldVille.setNom(ville.getNom());
            oldVille.setNbHabitants(ville.getNbHabitants());
            villeDao.update(oldVille);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    public boolean deleteVille(int id) {
        try {
            villeDao.deleteById(id);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    public List<Ville> findByDepartmentCodeOrderByNbInhabitantsDesc(String code, Integer n) {
        return villeDao.findByDepartmentCodeOrderByNbInhabitantsDesc(code, n);
    }

    public List<Ville> findByDepartmentCodeAndNbInhabitantsBetween(String code, Integer min, Integer max) {
        return villeDao.findByDepartmentCodeAndNbInhabitantsBetween(code, min, max);
    }

}
