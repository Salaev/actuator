package ru.otus.observability.actuator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ActuatorController {

    @GetMapping("/helloOtus")
    public String sayHello() {
        return "Привет, Otus!";
    }
}


