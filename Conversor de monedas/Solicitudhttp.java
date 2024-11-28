import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class Solicitudhttp {

    // Obtén la dirección de la API usando la clase ApiConfig
    String direccion = ApiConfig.getApiUrl();

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(direccion))
            .build();

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public String getRespuesta() {
        try {
            // Realiza la solicitud HTTP
            HttpResponse<String> respuesta = client.send(request, HttpResponse.BodyHandlers.ofString());
            return respuesta.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error al realizar la solicitud: " + e.getMessage();
        }
    }
}
