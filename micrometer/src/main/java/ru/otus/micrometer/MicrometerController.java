package ru.otus.micrometer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class MicrometerController {

    private final Random random = new Random();
    double lambda = 0.005;

    @GetMapping("/hello")
    public String sayHello() throws InterruptedException {
        long delay = getDelay();
        Thread.sleep(delay);
        return "Привет, Otus! (Delay: " + delay + ")";
    }

    private long getDelay() {
        double exponentialRandom = 100 + (-Math.log(1 - random.nextDouble()) / lambda);
        return (long) Math.min(exponentialRandom, 1000);
    }

}


