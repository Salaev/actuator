package ru.otus.micrometer;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MicrometerControllerMetricsAspect {

    private final Counter requestCounter;
    private final Timer requestTimer;

    public MicrometerControllerMetricsAspect(MeterRegistry meterRegistry) {
        // Счётчик запросов
        this.requestCounter = meterRegistry.counter("rest.controller.requests");

        // Таймер для измерения времени выполнения
        this.requestTimer = meterRegistry.timer("rest.controller.latency");
    }

    // Pointcut для методов, аннотированных как @RequestMapping, @GetMapping и т.д.
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "&& execution(public * ru.otus.micrometer.MicrometerController.*(..))"
    )
    public void requestMappingMethods() {
    }

    @Around("requestMappingMethods()")
    public Object trackExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // Засекаем время начала выполнения
        Timer.Sample sample = Timer.start();

        try {
            // Увеличиваем счётчик запросов
            requestCounter.increment();

            // Выполнение самого метода контроллера
            return joinPoint.proceed();
        } finally {
            // Фиксируем время выполнения метода
            sample.stop(requestTimer);
        }
    }
}


