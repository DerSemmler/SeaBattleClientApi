package org.thecircle.seabattleclient;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.thecircle.seabattleclient.payload.GameInfoPayload;
import org.thecircle.seabattleclient.payload.PlaceShipsRequest;
import org.thecircle.seabattleclient.payload.ShootRequest;

public class Api {

    private String serverAddress;
    private RestTemplate restTemplate;

    public Api(String serverAddress) {
        this.serverAddress = serverAddress;
        restTemplate = new RestTemplateBuilder().build();
    }

    public String startGame(String name) {
        String url = serverAddress + "/api/" + name + "/newgame";
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public String stopAllGames(int player, String password) {
        String url = serverAddress + "/api/stopall/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public String stopGame(String game, int player, String password) {
        String url = serverAddress + "/api/" + game + "/stop/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public String restartGame(String game, int player, String password) {
        String url = serverAddress + "/api/" + game + "/restart/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public String register(String game, int player, String password) {
        String url = serverAddress + "/api/" + game + "/register/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public GameInfoPayload getGameInfo(String game, int player, String password) {
        String url = serverAddress + "/api/" + game + "/info/" + player + "?pw=" + password;
        try {
            ResponseEntity<GameInfoPayload> response = restTemplate.getForEntity(url, GameInfoPayload.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean myTurn(String game, int player, String password) {
        String url = serverAddress + "/api/" + game + "/myturn/" + player + "?pw=" + password;
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            System.out.println(e);
            return false;
        }
    }

    public String skip(String game, int player, String password) {
        String url = serverAddress + "/api/" + game + "/skip/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public String placeShips(String game, int player, String password, PlaceShipsRequest request) {
        String url = serverAddress + "/api/" + game + "/placeships/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public String shoot(String game, int player, String password, ShootRequest request) {
        String url = serverAddress + "/api/" + game + "/shoot/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public String getWinner(String game, int player, String password) {
        String url = serverAddress + "/api/" + game + "/winner/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public String getVisual(String game, int player, String password, String arg) {
        String url = serverAddress + "/api/" + game + "/winner/" + arg + "/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }
}
