package com.moneyexchange.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Nos permite almacenar los datos de la conversion realizada.
public record ConversionRealizada(String origen, double monto, String destino, double resultado, String fechaHora) {
    //Constructor que nos permite obtener la fecha y hora actual al momento de realizarse la solicitud a la api
    public ConversionRealizada(String origen, double monto, String destino, double resultado) {
        this(origen, monto, destino, resultado,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    //Permitia separar los datos obtenidos por comas. El cual era util si necesitabamos exportar en un archivos .csv
    @Override
    public String toString(){
        return String.format("%s,%s,%.2f,%s,%.2f", fechaHora, origen, monto, destino, resultado);
    }
}
