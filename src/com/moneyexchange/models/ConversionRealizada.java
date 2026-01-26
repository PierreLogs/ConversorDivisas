package com.moneyexchange.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ConversionRealizada(String origen, double monto, String destino, double resultado, String fechaHora) {
    public ConversionRealizada(String origen, double monto, String destino, double resultado) {
        this(origen, monto, destino, resultado,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    @Override
    public String toString(){
        return String.format("%s,%s,%.2f,%s,%.2f", fechaHora, origen, monto, destino, resultado);
    }
}
