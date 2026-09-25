package com.spring.reservas.api.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PagoService {

    public boolean procesarPago(){
        System.out.println("procesando pago");
        return true;
    }

    public boolean pagoException (Exception e){
        System.out.println(e.getMessage());
        return false;
    }

}
