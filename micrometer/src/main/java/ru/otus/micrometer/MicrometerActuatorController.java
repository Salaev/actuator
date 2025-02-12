package ru.otus.micrometer;

import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator")
public class MicrometerActuatorController {
    private final PrometheusMeterRegistry meterRegistry;

    public MicrometerActuatorController(PrometheusMeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/prometheus")
    public String prometheus() {
        return meterRegistry.scrape();
    }
}


