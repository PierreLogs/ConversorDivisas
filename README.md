# 💱 Conversor de Monedas

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk)
![Gson](https://img.shields.io/badge/Gson-2.10.1-green?style=for-the-badge)
![License](https://img.shields.io/badge/license-MIT-blue?style=for-the-badge)

Aplicación de consola en Java que convierte entre múltiples divisas usando la [ExchangeRate-API](https://www.exchangerate-api.com/). Obtiene tasas de cambio en tiempo real, guarda historial de conversiones y maneja validación de datos.

## Funcionalidades

- **Conversión en tiempo real** vía API REST
- **Historial de consultas** exportado a `historial_conversiones.json`
- **Validación de datos** — manejo de entradas incorrectas y códigos inválidos
- **Resultados formateados** a dos decimales

## Stack

- Java 17+ con `java.net.http.HttpClient`
- Gson 2.10.1 para parsing JSON
- ExchangeRate-API como fuente de datos

## Cómo usar

```bash
# Compilar
javac -cp ".;gson-2.10.1.jar" src/com/moneyexchange/principal/Principal.java

# Ejecutar
java -cp ".;gson-2.10.1.jar" com.moneyexchange.principal.Principal
```

1. Obtén una API Key gratis en [exchangerate-api.com](https://www.exchangerate-api.com/)
2. Insértala en `ConsultarMoneda.java`:
   ```java
   URI url = URI.create("https://v6.exchangerate-api.com/v6/TU_API_KEY/latest/" + monedaBase);
   ```

## Licencia

MIT © [PierreLogs](https://github.com/PierreLogs)
