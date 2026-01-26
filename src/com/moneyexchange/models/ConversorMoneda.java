package com.moneyexchange.models;

import java.util.Map;
// Nos permite mapear directamente la estructura del JSON que nos devuelve la API
public record ConversorMoneda(String base_code, Map<String, Double> conversion_rates){

}