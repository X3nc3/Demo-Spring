package fr.diginamic.hello.mapper;
import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.entity.Departement;

import fr.diginamic.hello.entity.Ville;
import org.springframework.stereotype.Component;

@Component
public class DepartementMapper {

    public DepartementDto toDto(Departement departement) {
        int nbHabitantsDepartement = 0;
        for (Ville ville : departement.getVilles() ) {
            nbHabitantsDepartement += ville.getNbHabitants();
        }

        DepartementDto departementDto = new DepartementDto();
        departementDto.setCode(departement.getCode());
        departementDto.setNom(departement.getNom());
        departementDto.setNbHabitants(nbHabitantsDepartement);
        return departementDto;
    }

    public Departement toEntity(DepartementDto departementDto) {
        Departement departement = new Departement();
        departement.setCode(departementDto.getCode());
        departement.setNom(departementDto.getNom());
        return departement;
    }
}
