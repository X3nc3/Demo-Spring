package fr.diginamic.hello.mapper;
import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.entity.Ville;

import org.springframework.stereotype.Component;

@Component
public class VilleMapper {

    public VilleDto toDto(Ville ville) {
        VilleDto dto = new VilleDto();
        dto.setNom(ville.getNom());
        dto.setNbHabitants(ville.getNbHabitants());
        dto.setCodeDepartement(ville.getDepartement().getCode());
        dto.setNomDepartement(ville.getDepartement().getNom());
        return dto;
    }

    public Ville toEntity(VilleDto villeDto) {
        Ville ville = new Ville();
        ville.setNom(villeDto.getNom());
        ville.setNbHabitants(villeDto.getNbHabitants());
        ville.setDepartement(new Departement(villeDto.getNomDepartement(), villeDto.getCodeDepartement()));
        return ville;
    }
}
