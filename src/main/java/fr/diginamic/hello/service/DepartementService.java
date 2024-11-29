package fr.diginamic.hello.service;
import fr.diginamic.hello.repository.DepartementRepository;
import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.exception.Controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Departement findByCode(String code) throws Controle {
        if (departementRepository.findByCode(code) != null) {
            return departementRepository.findByCode(code);
        } else {
            throw new Controle("Le code département n'existe pas");
        }
    }

    public Departement findById(int id) throws Controle {
        if (departementRepository.findById(id).isPresent()) {
            return departementRepository.findById(id).get();
        } else {
            throw new Controle("L'id n'existe pas");
        }
    }

    private void validerDepartement (Departement departement) throws Controle {
        if (departement.getCode().length() < 2 && departement.getCode().length() >3) {
            throw new Controle("Le code département fait au maximum 3 caractères et au minimum 2");
        } else if (departement.getNom().length() < 3) {
            throw new Controle("Le nom du département est obligatoire et comporte au moins 3 lettres");
        } else if (departement.getCode().equals(departementRepository.findByCode(departement.getCode()).getCode())) {
            throw new Controle("Le code département doit etre unique");
        }
    }

    public void create(Departement departement) throws Controle {
        validerDepartement(departement);
        departementRepository.save(departement);
    }

    public void update(Departement departement) throws Controle {
        validerDepartement(departement);
        departementRepository.save(departement);
    }

    public void delete(int id) throws Controle {
        if (departementRepository.findById(id).isPresent()) {
            departementRepository.deleteById(id);
        } else {
            throw new Controle("L'id n'éxiste pas");
        }
    }

}
