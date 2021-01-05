package org.thecircle.seabattleclient;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class Api {

    private String serverAddress;
    private RestTemplate restTemplate;

    public Api(String serverAddress) {
        this.serverAddress = serverAddress;
        restTemplate = new RestTemplateBuilder().build();
    }

    public String startGame(String gameName) throws HttpClientErrorException {
        String url = serverAddress + "/api/" + gameName + "/newgame";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            return e.getMessage();
        }
    }


}
