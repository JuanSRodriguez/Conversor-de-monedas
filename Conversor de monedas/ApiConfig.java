public class ApiConfig {

    // Método para obtener la API Key de las variables de entorno
    public static String getApiKey() {
        String apiKey = System.getenv("API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API Key no encontrada en variables de entorno.");
        }
        return apiKey;
    }

    // Método para construir la URL de la API
    public static String getApiUrl() {
        String apiKey = getApiKey();
        return "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";
    }
}
