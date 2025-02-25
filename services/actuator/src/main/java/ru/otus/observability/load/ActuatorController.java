package ru.otus.observability.load;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ActuatorController {

    private final Logger logger = LoggerFactory.getLogger(ActuatorController.class);

    @GetMapping("/helloOtus")
    public String sayHello() {
        logger.info("Info");
        logger.debug("Debug");
        logger.warn("Warn");
        logger.error("Error");
        return "Привет, Otus!";
    }
}


