package com.moneyexchange.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneradorArchivos{
    public void guardarHistorial(List<ConversionRealizada>historial){
        //Nos permite obtener un Json legible
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //Creamos el archivo con extension .json
        try(FileWriter escritura = new FileWriter("historial_conversiones.json")){
            String jsonSalida = gson.toJson(historial);
            //Obtenemos la salida en formato Json
            escritura.write(jsonSalida);
            System.out.println("Archivo JSON generado exitosamente.");
        }catch(IOException e){
            System.out.println("Error al guardar el archivo JSON: "+e.getMessage());
        }
    }
}