package com.moneyexchange.principal;

import com.moneyexchange.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.io.IOException; // Importamos para manejar el error de archivo

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in).useLocale(Locale.US);
        CalculadorDeMoneda calculador = new CalculadorDeMoneda();
        ConsultarMoneda consulta = new ConsultarMoneda();
        GeneradorArchivos generador = new GeneradorArchivos(); // Instanciamos el generador
        int opcion = 0;
        String origen;
        String destino;

        List<ConversionRealizada> historial = new ArrayList<>();


        while(opcion != 2){
                        String menuDeOpciones = ("""
                    ===================================
                    BIENVENIDOS AL CONVERSOR DE MONEDAS
                    1. Realizar una converción
                    2. Salir y ver historial
                    ===================================
                    """);
            System.out.println(menuDeOpciones);

            try{
                opcion = Integer.parseInt(lectura.nextLine());
                if(opcion == 1){

                    System.out.println("Codigo de moneda ORIGEN (ej. USD, PEN, EUR, BRL):");
                    origen = lectura.nextLine().toUpperCase();

                    ConversorMoneda datos = consulta.buscarMoneda(origen);
                    var tasas = datos.conversion_rates();

                    System.out.println("Codigo de moneda Destino (ej: JPY, GBP, MXN):");
                    destino = lectura.nextLine().toUpperCase();

                    System.out.println("Cantidad a convertir:");
                    double cantidad = Double.parseDouble(lectura.nextLine());
                    double resultado = calculador.convertir(cantidad, origen, destino, tasas);

                    historial.add(new ConversionRealizada(origen,cantidad,destino,resultado));
                    System.out.printf(">>> %.2f %s equivale a %.2f %s <<<\n\n", cantidad,origen,resultado,destino);
                } else if(opcion == 2){
                    System.out.println("Guardando historial y saliendo...");
                    if(!historial.isEmpty()){
                        generador.guardarHistorial(historial);
                    }
                    System.out.println("Gracias por usar el conversor.");
                    System.out.println("========== HISTORIAL DE CONVERSIONES ==========");
                    for (int i = 0;i<historial.size();i++){
                        System.out.println((i+1)+" ."+historial.get(i));
                    }
                    break;
                }else{
                    System.out.println("Escoja una opciòn valida.");
                }
            }catch (Exception e){
                System.out.println("Error: "+e.getMessage());
            }
        }
    }
}
