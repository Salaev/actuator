package ru.otus.observability.actuator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfiguration {

    //@Bean
    @ConditionalOnProperty(name = "customConditional.enabled", havingValue = "true")
    public CustomConditional customConditional() {
        return new CustomConditional();
    }

}
