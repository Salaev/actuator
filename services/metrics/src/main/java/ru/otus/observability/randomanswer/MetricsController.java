package ru.otus.observability.randomanswer;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class MetricsController {

    private final MetricsClient metricsClient;

    public MetricsController(MetricsClient metricsClient) {
        this.metricsClient = metricsClient;
    }

    private final Random random = new Random();
    double lambda = 0.005;


    @GetMapping("/helloOtus/{name}")
    @Counted(value = "user.counted.helloOtus", description = "Подсчитывает количество запросов к сервису")
    @Timed(value = "user.timed.helloOtus", description = "Подсчитывает время выполнения")
    @Observed(name = "user.data.helloOtus")
    public String helloOtus(@PathVariable String name) throws InterruptedException {
        metricsClient.call(name);
        Thread.sleep(getDelay());
        return "Привет, Otus!";
    }

    private long getDelay() {
        double exponentialRandom = 100 + (-Math.log(1 - random.nextDouble()) / lambda);
        return (long) Math.min(exponentialRandom, 1000);
    }

}


