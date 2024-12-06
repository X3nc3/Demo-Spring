package fr.diginamic.hello.service;

import fr.diginamic.hello.repository.DepartementRepository;
import fr.diginamic.hello.repository.VilleRepository;
import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.exception.Controle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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

    public Ville getVille(int id) throws Controle {
        if (villeRepository.findById(id).isPresent()) {
            return villeRepository.findById(id).get();
        } else {
            throw new Controle("L'id n'existe pas");
        }
    }

    public Ville getVilleByNom(String nom) throws Controle {
        if (villeRepository.findByNom(nom) != null) {
            return villeRepository.findByNom(nom);
        } else {
            throw new Controle("Le nom n'existe pas");
        }
    }

    private void validerVille(Ville ville) throws Controle {
        if (ville.getNbHabitants() < 10) {
            throw new Controle("La ville doit avoir au moins 10 habitants");
        } else if (ville.getNom().length() < 2) {
            throw new Controle("La ville doit avoir un nom contenant au moins 2 lettres");
        } else if (ville.getDepartement().getCode().length() != 2) {
            throw new Controle("Le code département doit obligatoire 2 caractères");
        } else if (ville.getDepartement().getCode().equals(villeRepository.findByNom(ville.getNom()).getDepartement().getCode())) {
            throw new Controle("Le nom de la ville doit être unique pour un département donné.");
        } else if (ville.getDepartement().getCode() != null) {
            throw new Controle("Le code département est obligatoire.");
        }
    }

    public void addVille(Ville ville) throws Controle {
        validerVille(ville);
            Departement d = departementRepository.findByCode(ville.getDepartement().getCode());
                if (d == null) {
                    departementRepository.save(ville.getDepartement());
                }else {
                    ville.setDepartement(d);
                }
                villeRepository.save(ville);
    }

    public void updateVille(Ville ville) throws Controle {
        validerVille(ville);
            Optional<Ville> oldVille = villeRepository.findById(ville.getId());
            if (oldVille.isPresent()) {
                oldVille.get().setNom(ville.getNom());
                oldVille.get().setNbHabitants(ville.getNbHabitants());
                villeRepository.save(oldVille.get());
            }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteVille(int id) throws Controle {
        if (villeRepository.existsById(id)) {
            villeRepository.deleteById(id);
        } else {
            throw new Controle("L'id n'existe pas");
        }
    }

    public List<Ville> findByDepartementCodeOrderByNbHabitantsDesc(String code, Integer n) throws Controle {
        if (departementRepository.findByCode(code) == null) {
            throw new Controle("Le département " + code + " n'existe pas");
        }
        Pageable pageable = PageRequest.of(1, n);
        return villeRepository.findByDepartementCodeOrderByNbHabitantsDesc(code, pageable);
    }

    public List<Ville> findByNomStartingWithIgnoreCase(String nom) throws Controle {
        if (villeRepository.findByNomStartingWithIgnoreCase(nom) == null) {
            throw new Controle("Aucune ville dont le " + nom + " commence par nom n’a été trouvée");
        }
        return villeRepository.findByNomStartingWithIgnoreCase(nom);
    }

    public List<Ville> findByNbHabitantsGreaterThan(Integer min) throws Controle {
        if (villeRepository.findByNbHabitantsGreaterThan(min) == null) {
            throw new Controle("Aucune ville n’a une population supérieure à " + min);
        }
        return villeRepository.findByNbHabitantsGreaterThan(min);
    }

    public List<Ville> findByNbInhabitantsBetween(Integer min, Integer max) throws Controle {
        if (villeRepository.findByNbHabitantsBetween(min, max) == null) {
            throw new Controle(" Aucune ville n’a une population comprise entre" + min + " et " +max);
        }
        return villeRepository.findByNbHabitantsBetween(min, max);
    }

    public List<Ville> findByDepartementCodeAndNbHabitantsGreaterThan(String code, Integer min) throws Controle {
        if (villeRepository.findByDepartementCodeAndNbHabitantsGreaterThan(code, min) == null) {
            throw new Controle("Aucune ville n’a une population supérieure à " + min + " dans le département codeDept");
        }
        return villeRepository.findByDepartementCodeAndNbHabitantsGreaterThan(code,min);
    }

    public List<Ville> findByDepartementCodeAndNbHabitantsBetween(String code, Integer min, Integer max) throws Controle {
        if (villeRepository.findByDepartementCodeAndNbHabitantsBetween(code, min, max) == null) {
            throw new Controle(" Aucune ville n’a une population comprise entre " + min + " et " + max + " dans le département " + code);
        }
        return villeRepository.findByDepartementCodeAndNbHabitantsBetween(code, min, max);
    }

}
