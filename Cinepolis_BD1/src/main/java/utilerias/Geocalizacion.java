/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

/**
 *
 * @author Usuario
 */
public class Geocalizacion {

    public static String obtenerCoordenadas() throws IOException {
        URL url = new URL("http://ip-api.com/json");
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        JSONObject jsonObject = new JSONObject(response.toString());
        double latitud = jsonObject.getDouble("lat");
        double longitud = jsonObject.getDouble("lon");

        // Construir la cadena de coordenadas en el formato correcto
        String coordenadas = "POINT(" + latitud + " " + longitud + ")";

        return coordenadas;
    }

}
