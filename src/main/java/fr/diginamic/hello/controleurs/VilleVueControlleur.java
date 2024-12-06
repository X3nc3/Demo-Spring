package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.exception.Controle;
import fr.diginamic.hello.service.DepartementService;
import fr.diginamic.hello.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VilleVueControlleur {

    @Autowired
    VilleService villeService;

    @GetMapping("/villeList")
    public String getVilles(Model model, Authentication authentication) {
        model.addAttribute("authentication", authentication);
        model.addAttribute("villes", villeService.getAllVilles());
        return "ville/villeList";
    }

    @GetMapping("/deleteVille/{id}")
    public String deleteVille(@PathVariable int id) throws Controle {
        villeService.deleteVille(id);
        return "redirect:/villeList";
    }
}
