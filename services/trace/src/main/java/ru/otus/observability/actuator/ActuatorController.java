package ru.otus.observability.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class ActuatorController {

    private final Logger logger = LoggerFactory.getLogger(ActuatorController.class);

    private final Random random = new Random();
    double lambda = 0.005;

    @GetMapping("/hello")
    public String sayHello() throws InterruptedException {
        logger.info("Info{}", random.nextInt());
        logger.debug("Debug{}", random.nextInt());
        logger.warn("Warn{}", random.nextInt());
        logger.error("Error{}", random.nextInt());
        long delay = getDelay();
        Thread.sleep(delay);
        return "Привет, Otus! (Delay: " + delay + ")";
    }

    private long getDelay() {
        double exponentialRandom = 100 + (-Math.log(1 - random.nextDouble()) / lambda);
        return (long) Math.min(exponentialRandom, 1000);
    }

}


