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

    public GameInfo getGameInfo(String game, int player, String password) {
        String url = serverAddress + "/api/" + game + "/info/" + player + "?pw=" + password;
        try {
            ResponseEntity<GameInfo> response = restTemplate.getForEntity(url, GameInfo.class);
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

    public String placeShips(String game, int player, String password, ShipCoordinates[] shipCoordinates) {
        String url = serverAddress + "/api/" + game + "/placeships/" + player + "?pw=" + password;
        PlaceShipsRequest request = new PlaceShipsRequest(shipCoordinates);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }

    public String shoot(String game, int player, String password, int x, int y) {
        String url = serverAddress + "/api/" + game + "/shoot/" + player + "?pw=" + password;
        ShootRequest request = new ShootRequest(x, y);
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
        String url = serverAddress + "/api/" + game + "/visual/" + arg + "/" + player + "?pw=" + password;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            return e.getMessage();
        }
    }
}