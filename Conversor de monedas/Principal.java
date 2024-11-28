import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Principal {

    public static void main(String[] args) {

        // Llama a la API para obtener las tasas de conversión
        Solicitudhttp solicitud = new Solicitudhttp();
        String respuestaJson = solicitud.getRespuesta();

        // Verifica si la respuesta fue exitosa
        if (respuestaJson == null || respuestaJson.isEmpty()) {
            System.out.println("*************************************************");
            System.out.println("No se pudo obtener una respuesta válida de la API.");
            return;
        }

        JsonObject jsonObject;
        try {
            // Usa Gson para analizar la respuesta JSON
            jsonObject = JsonParser.parseString(respuestaJson).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            System.out.println("Error al analizar el JSON: " + e.getMessage());
            return;
        }

        // Verifica si el objeto JSON contiene 'conversion_rates'
        if (!jsonObject.has("conversion_rates")) {
            System.out.println("*************************************************");
            System.out.println("La respuesta JSON no contiene 'conversion_rates'.");
            return;
        }

        // Obtiene las tasas de conversión
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

        // Entrada de la moneda de origen
        Scanner scanner = new Scanner(System.in);
        int opcion = 1;
        String fromCurrency = "";
        String toCurrency = "";
        double amount = 0;

        while (opcion == 1) {
            System.out.println("*************************************************");
            System.out.println("Ingrese la moneda que quiere convertir (por ejemplo, USD, MXN, COP, etc.):");
            fromCurrency = scanner.nextLine().toUpperCase();

            // Valida que la moneda de origen esté en el JSON
            if (!conversionRates.has(fromCurrency)) {
                System.out.println("*************************************************");
                System.out.println("Moneda de entrada no válida.");
                continue;
            }

            System.out.println("*************************************************");
            System.out.println("Ingrese la moneda a la que quiere convertir (por ejemplo, USD, MXN, COP, etc.):");
            toCurrency = scanner.nextLine().toUpperCase();

            // Valida que la moneda de destino esté en el JSON
            if (!conversionRates.has(toCurrency)) {
                System.out.println("Moneda de salida no válida.");
                continue;
            }

            // Solicita el monto a convertir
            System.out.println("*************************************************");
            System.out.println("Ingrese el monto a convertir:");
            try {
                amount = scanner.nextDouble();
                scanner.nextLine(); // Consume el salto de línea
            } catch (Exception e) {
                System.out.println("*************************************************");
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpia el buffer

                System.out.println("*************************************************");
                System.out.println("¿Desea intentar nuevamente? (1: Sí, 2: Salir)");
                String entrada = scanner.nextLine();

                try {
                    opcion = Integer.parseInt(entrada);
                    if (opcion != 1 && opcion != 2) {
                        System.out.println("Opción inválida. Saliendo del programa.");
                        break;
                    }
                } catch (Exception ex) {
                    System.out.println("Opción inválida. Saliendo del programa.");
                    break;
                }

                if (opcion == 2) {
                    break;
                }
                continue;
            }

            // Realiza la conversión y muestra el resultado
            double fromRate = conversionRates.get(fromCurrency).getAsDouble();
            double toRate = conversionRates.get(toCurrency).getAsDouble();
            DecimalFormat df = new DecimalFormat("#.###");
            double convertedAmount = (amount / fromRate) * toRate;

            System.out.println(
                    amount + " " + fromCurrency + " equivale a " + df.format(convertedAmount) + " " + toCurrency);
            System.out.println(
                    "Tasa de conversión: 1 " + fromCurrency + " = " + df.format(toRate / fromRate) + " " + toCurrency);

            // Pregunta al usuario si desea continuar
            System.out.println("*************************************************");
            System.out.println("¿Desea continuar operando? (1: Sí, 2: Salir)");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consume el salto de línea
            } catch (Exception e) {
                System.out.println("Opción inválida. Saliendo del programa.");
                break;
            }

            if (opcion != 1 && opcion != 2) {
                System.out.println("Opción inválida. Saliendo del programa.");
                break;
            }
        }

        // Cierra el scanner al final
        scanner.close();
    }
}
