package org.polytech.covid.Patient.metric;

import java.time.Instant;
import java.util.List;
import java.time.Duration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;


@Aspect
public class MetricsPatientAspect {
    private final MeterRegistry registry;
    
    @Autowired
    public MetricsPatientAspect(MeterRegistry registry) {
        this.registry = registry;
    }

    @AfterReturning("execution(public * org.polytech.covid.Patient.service.patientService.save(org.polytech.covid.Patient.files.patient))")
    public void successPatientAdd(JoinPoint joinPoint) {
        System.out.println("################### ICI #####################################");
        Tag tag = Tag.of("patient-ajout", joinPoint.getTarget().getClass().getSimpleName());
        registry.counter("patient-added", List.of(tag)).increment();
    }

    @AfterReturning("execution(public * org.polytech.covid.Patient.service.patientService.update(* *(..)))")
    public void successPatientUpdate(JoinPoint joinPoint) {
        Tag tag = Tag.of("patient-update", joinPoint.getTarget().getClass().getSimpleName());
        registry.counter("patient-updated", List.of(tag)).increment();
    }


    @Around("execution(public * org.polytech.covid.Patient.service.patientService.save(* *(..)))")
    public Object tempsCreation(ProceedingJoinPoint joinPoint)
            throws Throwable {
        Tag tag = Tag.of("patient-ajout", joinPoint.getTarget().getClass().getSimpleName());
        Timer timer = registry.timer("patient-creat-duration", List.of(tag));
        Instant startTime = Instant.now();
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            timer.record(Duration.between(startTime, Instant.now()));
        }
        
    }
}
