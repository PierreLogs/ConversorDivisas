package com.moneyexchange.models;

import java.util.Map;

public class CalculadorDeMoneda {
    public double convertir(double cantidad, String origen, String destino, Map<String, Double> tasas){
        //Verifica si ambos còdigos existen en el mapa de la API
        if(!tasas.containsKey(origen)||!tasas.containsKey(destino)){
            throw new IllegalArgumentException("Uno de los còdigos de moneda no es vàlido.");
        }
        //Lògica de "Moneda Puente"
        double enUSD = origen.equals("USD") ? cantidad : cantidad / tasas.get(origen);
        return Math.round(enUSD * tasas.get(destino));
    }
}
