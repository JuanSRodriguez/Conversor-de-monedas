# Conversor de Monedas 💱

Este es un programa de consola que permite realizar conversiones entre diferentes monedas utilizando la API **ExchangeRate-API** para obtener tasas de cambio en tiempo real. Además, se emplea la biblioteca **Gson** para procesar datos JSON de manera eficiente.

## **Características**

- **Conversión**: Utiliza tasas de cambio en tiempo real proporcionadas por **ExchangeRate-API**.
- **Soporte para múltiples monedas**: Compatible con divisas comunes como USD, COP, EUR, MXN, entre otras.
- **Interfaz interactiva**: Menú sencillo para ingresar monedas, montos y realizar varias conversiones en una sola sesión.
- **Procesamiento de JSON**: Usa **Gson** para manejar y analizar las respuestas JSON de la API.
- **Seguridad**: La clave de la API se gestiona mediante variables de entorno para proteger la información sensible.

## **Funcionamiento del Programa**

### **Selección de Monedas**:

- Ingresa la moneda de origen (por ejemplo, **USD**).
- Ingresa la moneda de destino (por ejemplo, **COP**).

### **Monto a Convertir**:

- Escribe el monto que deseas convertir en la moneda de origen.

### **Resultados**:

- El programa utiliza **ExchangeRate-API** para obtener la tasa de conversión actual.
- Usa **Gson** para analizar el JSON recibido de la API.
- Muestra el resultado del monto convertido y la tasa de cambio utilizada.

### **Opciones Adicionales**:

- Al finalizar una conversión, puedes realizar otra o salir del programa.
EJ:

![image](https://github.com/user-attachments/assets/bbc37536-5dd6-43bf-abfe-2ca416d96e45)

- Para que el código funcione correctamente se debe configurar la apiKey.
- Se debe integrar la libreria de Gson.

