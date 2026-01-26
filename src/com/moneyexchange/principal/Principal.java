package com.moneyexchange.principal;

import com.moneyexchange.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        //Se configuro la lectura de decimales con punto (.), en lugar de coma(,)
        Scanner lectura = new Scanner(System.in).useLocale(Locale.US);

        //Instanciamos las clases de apoyo
        CalculadorDeMoneda calculador = new CalculadorDeMoneda();
        ConsultarMoneda consulta = new ConsultarMoneda();
        GeneradorArchivos generador = new GeneradorArchivos();

        int opcion = 0; //Permite controlar el ciclo del menu en 0
        String origen; //Variable que permite almacenar la moneda base
        String destino;//Variable que permite almacenar la moneda destino

        //Lista dinamica que permite guardar cada conversion exitosa en la memoria
        List<ConversionRealizada> historial = new ArrayList<>();

        //Ciclo que se ejecuta hasta que el usuario elija la opcion 2 para salir y guardar el historial de conversiones
        while(opcion != 2){
            String menuDeOpciones = ("""
                    ===================================
                    BIENVENIDOS AL CONVERSOR DE MONEDAS
                    1. Realizar una converción
                    2. Salir y ver historial
                    ===================================
                    """);
            System.out.println(menuDeOpciones);

            //Iniciamos el manejo de excepciones durante la ejecucion del codigo.
            try{
                //Las opciones a elegir seran en formato entero
                opcion = Integer.parseInt(lectura.nextLine());
                if(opcion == 1){

                    //Permite obtener la moneda de origen y solicitar la tasa indicada a la API
                    System.out.println("Codigo de moneda ORIGEN (ej. USD, PEN, EUR, BRL):");
                    origen = lectura.nextLine().toUpperCase();

                    //Permite llamar al metodo que nos conecta con la API de ExchangeRate-API
                    ConversorMoneda datos = consulta.buscarMoneda(origen);
                    //Obtiene el mapa de tasas (Key: Moneda, Value: valor)
                    if(datos == null || datos.conversion_rates() == null){
                        //logica con mensaje de error en caso el valor ingresado no sea el correcto o falle la conexion a la API.
                        System.out.println("No se pudieron obtener las tasas. Verifique el codigo de moneda o su conexion.");
                        continue;
                    }

                    var tasas = datos.conversion_rates();

                    //Obtiene la moneda destino y la cantidad a ingresar.
                    System.out.println("Codigo de moneda Destino (ej: JPY, GBP, MXN):");
                    destino = lectura.nextLine().toUpperCase();

                    System.out.println("Cantidad a convertir:");
                    double cantidad = Double.parseDouble(lectura.nextLine());

                    //Realiza el calculo matematico de la conversion.
                    double resultado = calculador.convertir(cantidad, origen, destino, tasas);

                    //Guarda en el historial las conversiones realizadas e imprime el resultado en la consola.
                    historial.add(new ConversionRealizada(origen,cantidad,destino,resultado));
                    System.out.printf(">>> %.2f %s equivale a %.2f %s <<<\n\n", cantidad,origen,resultado,destino);
                } else if(opcion == 2){
                    //Finaliza el programa mientras guarda el historial en un archivo externo si no esta vacio.
                    System.out.println("Guardando historial y saliendo...");
                    if(!historial.isEmpty()){
                        generador.guardarHistorial(historial);
                    }
                    System.out.println("Gracias por usar el conversor.");
                    System.out.println("========== HISTORIAL DE CONVERSIONES ==========");
                    //Recorre la lista del historial e imprime cada registro.
                    for (int i = 0;i<historial.size();i++){
                        System.out.println((i+1)+" ."+historial.get(i));
                    }
                    //Finaliza el programa luego de ejecutarse.
                    break;
                }else{
                    //Se imprime en pantalla este mensaje si introducimos un numero equivocado.
                    System.out.println("Escoja una opciòn valida.");
                }
            }catch (Exception e){
                //Captura errores de entrada (letras en lugar de numeros).
                System.out.println("Error: "+e.getMessage());
            }
        }
    }
}
