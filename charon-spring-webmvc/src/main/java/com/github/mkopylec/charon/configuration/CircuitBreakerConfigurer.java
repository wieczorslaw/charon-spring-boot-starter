package com.github.mkopylec.charon.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

public class CircuitBreakerConfigurer {

    private CircuitBreakerConfiguration circuitBreakerConfiguration;

    private CircuitBreakerConfigurer() {
        circuitBreakerConfiguration = new CircuitBreakerConfiguration();
    }

    public static CircuitBreakerConfigurer circuitBreaker() {
        return new CircuitBreakerConfigurer();
    }

    public CircuitBreakerConfigurer configure(CircuitBreakerConfig.Builder circuitBreakerConfigBuilder) {
        circuitBreakerConfiguration.setCircuitBreakerConfig(circuitBreakerConfigBuilder.build());
        return this;
    }

    public CircuitBreakerConfigurer measured(boolean measured) {
        circuitBreakerConfiguration.setMeasured(measured);
        return this;
    }

    CircuitBreakerConfiguration getConfiguration() {
        return circuitBreakerConfiguration;
    }
}
