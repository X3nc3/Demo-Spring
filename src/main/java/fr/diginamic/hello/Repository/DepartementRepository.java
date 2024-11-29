package fr.diginamic.hello.Repository;
import fr.diginamic.hello.entity.Departement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {

    Departement findByCode(String code);
}
