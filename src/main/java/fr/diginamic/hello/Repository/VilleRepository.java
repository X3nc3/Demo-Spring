package fr.diginamic.hello.Repository;

import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.entity.Ville;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface VilleRepository extends JpaRepository<Ville, Integer> {

    Ville findByNom(String nom);

    List<Ville> findByDepartementCodeOrderByNbHabitantsDesc(String code, Pageable pageable);

    List<Ville> findByNbHabitantsBetween(Integer min, Integer max);

    List<Ville> findByNomStartingWithIgnoreCase(String nom);

    List<Ville> findByNbHabitantsGreaterThan(Integer min);

    List<Ville> findByDepartementCodeAndNbHabitantsGreaterThan(String code, Integer min);

    List<Ville> findByDepartementCodeAndNbHabitantsBetween(String code, Integer min, Integer max);

}
