package ru.otus.observability.randomanswer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class MicrometerController {

    private final Logger logger = LoggerFactory.getLogger(MicrometerController.class);

    private final Random random = new Random();
    double lambda = 0.005;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/helloOtus")
    public String helloOtus() throws InterruptedException {
        Thread.sleep(getDelay());
        return "Привет, Otus!" + getRandomResponse();
    }

    private long getDelay() {
        double exponentialRandom = 100 + (-Math.log(1 - random.nextDouble()) / lambda);
        return (long) Math.min(exponentialRandom, 1000);
    }

    private String getRandomResponse() {
        String serviceUrl = "http://localhost:8085/api/random-response";
        ResponseEntity<String> response = restTemplate.getForEntity(serviceUrl, String.class);
        logger.info("Received response: {} - {}", response.getStatusCode(), response.getBody());
        return response.getBody();
    }

}


