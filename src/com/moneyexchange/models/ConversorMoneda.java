package com.moneyexchange.models;

import java.util.Map;

public record ConversorMoneda(String base_code, Map<String, Double> conversion_rates){

}