package ru.otus.observability.randomanswer.customprometheus;

import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@RequestMapping("/actuator")
public class MicrometerMetricsController {
    private final PrometheusMeterRegistry meterRegistry;

    public MicrometerMetricsController(PrometheusMeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/prometheus")
    public String prometheus() {
        return meterRegistry.scrape();
    }
}


