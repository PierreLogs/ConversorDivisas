package com.moneyexchange.principal;

import com.moneyexchange.models.ConsultarMoneda;
import com.moneyexchange.models.ConversorMoneda;
import com.moneyexchange.models.GeneradorArchivos; // Importamos la clase

import java.util.Scanner;
import java.io.IOException; // Importamos para manejar el error de archivo

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultarMoneda consulta = new ConsultarMoneda();
        GeneradorArchivos generador = new GeneradorArchivos(); // Instanciamos el generador
        int opcionOrigen = 0;

        String menuMonedas = """
            ==================================================
            Bienvenido al conversor de Monedas
            ==================================================
            Elige la MONEDA DE ORIGEN:
            1. ARS - Peso Argentino
            2. BOB - Boliviano boliviano
            3. BRL - Real brasileño
            4. CLP - Peso chileno
            5. COP - Peso colombiano
            6. USD - Dolar estadounidense
            7. Salir
            ==================================================
            """;

        try {
            while (opcionOrigen != 7) {
                System.out.println(menuMonedas);
                opcionOrigen = lectura.nextInt();

                if (opcionOrigen == 7) break;

                String monedaBase = switch (opcionOrigen) {
                    case 1 -> "ARS";
                    case 2 -> "BOB";
                    case 3 -> "BRL";
                    case 4 -> "CLP";
                    case 5 -> "COP";
                    case 6 -> "USD";
                    default -> "Error";
                };

                if (monedaBase.equals("Error")) {
                    System.out.println("Opción de moneda base no válida.");
                    continue;
                }

                System.out.println("Consultando tasas para la moneda base: " + monedaBase + "...");
                // Obtenemos el objeto conversor completo de la API
                ConversorMoneda conversor = consulta.buscarMoneda(monedaBase);
                var tasas = conversor.conversion_rates();

                System.out.println("\n" + menuMonedas.replace("ORIGEN", "DESTINO"));
                System.out.println("Elige la MONEDA DE DESTINO:");
                int opcionDestino = lectura.nextInt();

                if (opcionDestino == 7) break;

                String monedaDestino = switch (opcionDestino) {
                    case 1 -> "ARS";
                    case 2 -> "BOB";
                    case 3 -> "BRL";
                    case 4 -> "CLP";
                    case 5 -> "COP";
                    case 6 -> "USD";
                    default -> "Error";
                };

                if (monedaDestino.equals("Error")) {
                    System.out.println("Opción de moneda destino no válida.");
                    continue;
                }

                System.out.println("Ahora ingresa el monto en " + monedaBase + " que deseas convertir:");
                double cantidad = lectura.nextDouble();

                double tasa = tasas.get(monedaDestino);
                double total = tasa * cantidad;

                System.out.printf("\n>>> El valor de %.2f %s equivale a: %.2f %s\n\n", cantidad, monedaBase, total, monedaDestino);

                // >>>>>> INTEGRACIÓN DEL GENERADOR DE ARCHIVOS <<<<<<
                try {
                    // Le pasamos el objeto 'conversor' completo al generador para que lo guarde
                    generador.guardarJson(conversor, monedaBase);
                    System.out.println("Se ha generado un archivo JSON con las tasas de " + monedaBase + ".");
                } catch (IOException e) {
                    System.out.println("Error al intentar guardar el archivo JSON: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }

        System.out.println("Gracias por usar el conversor");
        lectura.close();
    }
}
