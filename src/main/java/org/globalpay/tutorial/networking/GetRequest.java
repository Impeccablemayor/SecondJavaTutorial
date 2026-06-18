package org.globalpay.tutorial.networking;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/1/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);

                }

                in.close();
                //System.out.println(content);
                ResponseDto dto = mapper.readValue(content.toString(), ResponseDto.class);
                dto.setBody("This is the response body");
                System.out.println(dto.getBody());

            } else {
                System.out.println("Request failed with code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
