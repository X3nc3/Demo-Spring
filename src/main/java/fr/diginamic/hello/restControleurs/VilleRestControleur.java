package fr.diginamic.hello.restControleurs;

import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleRestControleur {

    @Autowired
    VilleService villeService;

    @GetMapping
    public List<Ville> listeVilles(){
        return villeService.getVilles();
    }


}
