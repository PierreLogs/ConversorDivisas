package com.moneyexchange.models;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ConsultarMoneda{
    public ConversorMoneda buscarMoneda(String monedaBase){
        URI url = URI.create("https://v6.exchangerate-api.com/v6/684c3e649580460683672fb9/latest/"+monedaBase);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(url).build();

        try{
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), ConversorMoneda.class);
        } catch (Exception e) {
            throw new RuntimeException("No hemos encontrado la moneda que ingresaste."+e.getMessage());
        }
    }
}