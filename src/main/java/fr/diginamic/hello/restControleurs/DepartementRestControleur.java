package fr.diginamic.hello.restControleurs;
import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.exception.Controle;
import fr.diginamic.hello.mapper.DepartementMapper;
import fr.diginamic.hello.service.DepartementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementRestControleur {

    @Autowired
    DepartementService departementService;

    @Autowired
    DepartementMapper departementMapper;

    @GetMapping
    public List<DepartementDto> getDepartements() {
        Iterable<Departement> departements = departementService.findAll();
        List<DepartementDto> departementDtos = new ArrayList<>();
        for (Departement departement : departements) {
            departementDtos.add(departementMapper.toDto(departement));
        }
        return departementDtos;
    }

    @GetMapping("{id}")
    public DepartementDto getDepartementById(@PathVariable int id) throws Controle {
        return departementMapper.toDto(departementService.findById(id));
    }

    @GetMapping("/{code}")
    public DepartementDto getDepartemenByCode(@PathVariable String code) throws Controle {
        return departementMapper.toDto(departementService.findByCode(code));
    }

    @PostMapping
    public ResponseEntity<String> postDepartement(@RequestBody DepartementDto departementDto) throws Controle {
        departementService.create(departementMapper.toEntity(departementDto));
        return ResponseEntity.ok("Département crée avec succès");
    }

    @PutMapping
    public ResponseEntity<String> putDepartement(@RequestBody DepartementDto departementDto) throws Controle {
        departementService.update(departementMapper.toEntity(departementDto));
        return ResponseEntity.ok().body("Le département " + departementDto.getNom() + " à etait modifiée");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteDepartement(@PathVariable int id) throws Controle {
        departementService.delete(id);
        return ResponseEntity.ok().body("La ville avec l'id : " + id + " est supprimée");
    }
}
