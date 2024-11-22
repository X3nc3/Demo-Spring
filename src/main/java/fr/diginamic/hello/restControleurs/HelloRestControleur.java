package fr.diginamic.hello.restControleurs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloRestControleur {
    private String message = "Hello World !";

    @GetMapping
    public String sayHello() {
        return message.toUpperCase();
    }
}
