# 💱 Conversor de Monedas - Java Challenge

Bienvenido al sistema de intercambio de divisas desarrollado en Java. Este proyecto fue creado como parte del desafío de formación en el programa Alura Latam.

## 🚀 Funcionalidades
- **Conversión en Tiempo Real:** Obtiene tasas de cambio actualizadas vía API.
- **Historial de Consultas:** Guarda automáticamente tus operaciones en un archivo `historial_conversiones.json`.
- **Formato Limpio:** Los resultados se muestran redondeados a dos decimales para mayor claridad.
- **Validación de Datos:** Manejo de entradas incorrectas y códigos de moneda no existentes.

## 🛠️ Tecnologías Utilizadas
- **Java 17** (o superior)
- **Gson 2.10.1** (Para el manejo de JSON)
- **HttpClient** (Para las peticiones a la API)
- **ExchangeRate-API** (Fuente de datos)

## 📋 Estructura del Proyecto
El proyecto sigue una estructura organizada por paquetes:
- `com.moneyexchange.principal`: Contiene la clase `Principal` con el menú interactivo.
- `com.moneyexchange.models`: Contiene los `Records` y clases de servicio como `ConsultarMoneda`, `CalculadorDeMoneda` y `GeneradorArchivos`.

## ⚙️ Configuración
1. Obtén tu llave gratuita en [ExchangeRate-API](https://www.exchangerate-api.com/).
2. En la clase `ConsultarMoneda.java`, reemplaza el espacio de la URL con tu llave:
   ```java
   URI url = URI.create("https://v6.exchangerate-api.com/v6/TU_API_KEY/latest/" + monedaBase);
    ```
3. Asegúrate de agregar la librería Gson a las dependencias de tu proyecto.

## 📄 Ejemplo de Uso
Al ejecutar el programa, verás un menú como este:

```
1. Realizar una conversión
2. Salir y ver historial
```

Si eliges la opción 1, ingresas el código de origen (ej. USD), el de destino (ej. PEN) y el monto. ¡El sistema hará el resto!

Desarrollado por [PierreLogs](https://github.com/PierreLogs)
