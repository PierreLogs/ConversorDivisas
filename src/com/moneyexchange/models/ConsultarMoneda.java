package com.moneyexchange.models;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarMoneda{
    public ConversorMoneda buscarMoneda(String monedaBase){
        //Construimos la URL dinamica con tu API key y la moneda que el usuario eligira.
        //Aqui debes ingresar tu APIKey
        String tuApiKey = "";
        //URL de https://www.exchangerate-api.com/
        URI url = URI.create("https://v6.exchangerate-api.com/v6/"+tuApiKey+"/latest/"+monedaBase);
        //Creamos el cliente y la solicitud HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(url).build();

        try{
            //Enviamos la solicitud y recibimos la respuesta como una cadena de texto en formato JSON
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            //Permite transformar el JSON recibido en un objeto Java "ConversorMoneda" usando Gson
            //Debes descargar el paquete Gson en formato .jar e importar la libreria com.google.gson.Gson
            return new Gson().fromJson(response.body(), ConversorMoneda.class);
        } catch (Exception e) {
            //Si codigo de moneda no existe o no hay internet, lazamos una excepcion
            throw new RuntimeException("No hemos encontrado la moneda que ingresaste."+e.getMessage());
        }
    }
}