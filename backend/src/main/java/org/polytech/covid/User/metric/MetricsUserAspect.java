package org.polytech.covid.User.metric;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.micrometer.core.instrument.MeterRegistry;
// import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;

@Component
@Aspect
public class MetricsUserAspect {
    private final MeterRegistry registry;
    
    @Autowired
    public MetricsUserAspect(MeterRegistry registry) {
        this.registry = registry;
    }
    
    @AfterReturning("execution(public * org.polytech.covid.User.service.userService.saveAll())")
    public void successUserAdd(JoinPoint joinPoint) {
        Tag tag = Tag.of("user-impl", joinPoint.getTarget().getClass().getSimpleName());
        registry.counter("user-added", List.of(tag)).increment();
    }

    @AfterReturning("execution(public * org.polytech.covid.User.service.userService.delete())")
    public void successUserDelete(JoinPoint joinPoint) {
        Tag tag = Tag.of("user-delete", joinPoint.getTarget().getClass().getSimpleName());
        registry.counter("user-deleted", List.of(tag)).increment();
    }


    @Around("execution(public * org.polytech.covid.User.service.userService.saveAll())")
    public Object tempsCreation(ProceedingJoinPoint joinPoint)
            throws Throwable {
        Tag tag = Tag.of("user-impl", joinPoint.getTarget().getClass().getSimpleName());
        Timer timer = registry.timer("user-impl-duration", List.of(tag));
        Instant startTime = Instant.now();
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            timer.record(Duration.between(startTime, Instant.now()));
        }
        
    }
}
