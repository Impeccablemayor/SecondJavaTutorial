package org.globalpay.tutorial.networking;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

public class ExGetRequest {
    private static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) {

        try{
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = br.readLine()) != null) {
                    content.append(inputLine);
                }
                System.out.println(content);

                List dto = mapper.readValue(content.toString(), List.class);

                for (Object responseDto : dto) {
                    ResponseDto rDto = mapper.convertValue(responseDto, ResponseDto.class);
                    System.out.println("=========================================");
                    System.out.println("USER ID : " + rDto.getUserId());
                    System.out.println(responseDto);
                    System.out.println("=========================================");
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

}
