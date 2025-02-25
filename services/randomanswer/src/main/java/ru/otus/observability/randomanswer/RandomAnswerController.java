package ru.otus.observability.randomanswer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class RandomAnswerController {

    private final Logger logger = LoggerFactory.getLogger(RandomAnswerController.class);

    private final Random random = new Random();
    double lambda = 0.005;

    private static final List<HttpStatus> STATUSES = List.of(
            HttpStatus.OK, HttpStatus.OK, HttpStatus.OK, HttpStatus.OK, HttpStatus.OK,
            HttpStatus.OK, HttpStatus.OK, HttpStatus.OK, HttpStatus.OK, HttpStatus.OK,
            HttpStatus.OK, HttpStatus.OK, HttpStatus.OK, HttpStatus.OK, HttpStatus.OK,// Увеличиваем вероятность OK
            HttpStatus.BAD_REQUEST, HttpStatus.FORBIDDEN, HttpStatus.SERVICE_UNAVAILABLE
    );

    @GetMapping("/random-response")
    public ResponseEntity<String> getRandomResponse() throws InterruptedException {
        Thread.sleep(getDelay());
        HttpStatus randomStatus = STATUSES.get(random.nextInt(STATUSES.size()));
        if (randomStatus == HttpStatus.OK)
            logger.info("Received response: OK");
        else logger.error("Received response: ERROR");
        return ResponseEntity.status(randomStatus).body("Response with status: " + randomStatus);
    }

    private long getDelay() {
        double exponentialRandom = 100 + (-Math.log(1 - random.nextDouble()) / lambda);
        return (long) Math.min(exponentialRandom, 1000);
    }
}


