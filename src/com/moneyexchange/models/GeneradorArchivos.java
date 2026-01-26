package com.moneyexchange.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneradorArchivos{
    public void guardarHistorial(List<ConversionRealizada>historial){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter escritura = new FileWriter("reporte.csv")){
            escritura.write("Fecha y Hora, Moneda Origen, Monto Origen, Moneda Destino, Monto Destino\n");

            for(ConversionRealizada item:historial){
                escritura.write(item.toString()+"\n");
            }
            System.out.println("Reporte con marcas de tiempo generado");
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}