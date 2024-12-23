package fr.diginamic.hello.restControleurs;

import fr.diginamic.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloRestControleur {

    @Autowired
    private HelloService helloService;

    @GetMapping
    public String direHello() {
        return helloService.salutation();
    }
}
