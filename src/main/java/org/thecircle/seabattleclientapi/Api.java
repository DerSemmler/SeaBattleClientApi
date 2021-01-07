package org.thecircle.seabattleclientapi;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

public class Api {

    private String serverAddress;
    private RestTemplate restTemplate;

    public Api(String serverAddress, String basicAuthUser, String basicAuthPassword) {
        this.serverAddress = serverAddress;
        restTemplate = new RestTemplateBuilder().basicAuthentication(basicAuthUser, basicAuthPassword).build();
    }

    public Api(String serverAddress) {
        this.serverAddress = serverAddress;
        restTemplate = new RestTemplateBuilder().build();
    }

    public void activeBasicAuthentication(String basicAuthUser, String basicAuthPassword) {
        restTemplate = new RestTemplateBuilder().basicAuthentication(basicAuthUser, basicAuthPassword).build();
    }

    public void deactivateBasicAuthentication() {
        restTemplate = new RestTemplateBuilder().build();
    }

    public String startGame(String gameName) {
        String url = serverAddress + "/api/" + gameName + "/newgame";
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

    public String stopGame(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/stop/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public String restartGame(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/restart/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public String register(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/register/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public GameInfo getGameInfo(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/info/" + player + "?pw=" + password;
        try {
            ResponseEntity<GameInfo> response = restTemplate.getForEntity(url, GameInfo.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean myTurn(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/myturn/" + player + "?pw=" + password;
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            System.out.println(e);
            return false;
        }
    }

    public String skip(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/skip/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public String placeShips(String gameName, int player, String password, ShipCoordinates[] shipCoordinates) {
        String url = serverAddress + "/api/" + gameName + "/placeships/" + player + "?pw=" + password;
        PlaceShipsRequest request = new PlaceShipsRequest(shipCoordinates);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public String shoot(String gameName, int player, String password, int x, int y) {
        String url = serverAddress + "/api/" + gameName + "/shoot/" + player + "?pw=" + password;
        ShootRequest request = new ShootRequest(x, y);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public int getWinner(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/winner/" + player + "?pw=" + password;
        try {
            ResponseEntity<Integer> response = restTemplate.getForEntity(url, Integer.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            System.out.println(e);
            return -1;
        }
    }

    public String getVisual(String gameName, int player, String password, String arg) {
        String url = serverAddress + "/api/" + gameName + "/visual/" + arg + "/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }
}
