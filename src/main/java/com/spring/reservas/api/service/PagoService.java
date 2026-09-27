package com.spring.reservas.api.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PagoService {

    @CircuitBreaker(name = "pagoCircuitBreaker", fallbackMethod = "pagoException")
    public boolean procesarPago(){
        System.out.println(":::: procesando pago ::::::");

        //throw new RuntimeException("Servicio externo de pagos caído");

        return true;
    }

    public boolean pagoException (Throwable e){

        System.out.println("FALLBACK ACTIVO - Razón: " + e.getMessage());
        return false;
    }

}
