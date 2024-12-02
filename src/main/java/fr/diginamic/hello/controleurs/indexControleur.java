package fr.diginamic.hello.controleurs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexControleur {

    @GetMapping
    public String getIndex() {
        return "index";
    }
}
