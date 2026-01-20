package com.moneyexchange.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeneradorArchivos{
    public void guardarJson(ConversorMoneda conversorMoneda, String monedaBase) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter escritura = new FileWriter("tasas_"+monedaBase+".json");
        escritura.write(gson.toJson(conversorMoneda));
        escritura.close();
    }
}