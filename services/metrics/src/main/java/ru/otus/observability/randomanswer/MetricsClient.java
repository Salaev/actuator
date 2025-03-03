package ru.otus.observability.randomanswer;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MetricsClient {
    private final Random random = new Random();

    @Observed(name = "user.data.call",
            contextualName = "user-data-call",
            lowCardinalityKeyValues = {"userType", "userType2"})
    public void call(String name) {
        int randomNumber = random.nextInt(10);
        switch (randomNumber) {
            case 0:
                throw new NullPointerException("Случайное исключение: NullPointerException");
            case 1:
                throw new ArithmeticException("Случайное исключение: ArithmeticException");
            case 2:
                throw new IllegalArgumentException("Случайное исключение: IllegalArgumentException");
            default:
        }
    }
}
