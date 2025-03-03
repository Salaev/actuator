package ru.otus.observability.randomanswer.customprometheus;

import io.micrometer.prometheusmetrics.PrometheusConfig;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import org.springframework.context.annotation.Bean;

//@Configuration
public class ConfigurationApplication {

    @Bean
    public PrometheusMeterRegistry meterRegistry() {
        return new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
    }
}
