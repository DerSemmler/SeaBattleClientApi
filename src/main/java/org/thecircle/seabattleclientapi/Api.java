package org.thecircle.seabattleclientapi;

import lombok.Getter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

public class Api {

    private String serverAddress;
    private RestTemplate restTemplate;

    @Getter
    private ServerResponse serverResponse;

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

    public ServerResponse newGame(String gameName, int boardSize, String shipSet) {
        String url = serverAddress + "/api/" + gameName + "/newgame?boardsize=" + boardSize
                + "&shipset=" + shipSet;
        serverResponse = getMapping(url);
        return serverResponse;
    }

    public ServerResponse stopAllGames(int player, String password) {
        String url = serverAddress + "/api/stopall/" + player + "?pw=" + password;
        serverResponse = getMapping(url);
        return serverResponse;
    }

    public ServerResponse stopGame(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/stop/" + player + "?pw=" + password;
        serverResponse = getMapping(url);
        return serverResponse;
    }

    public ServerResponse restartGame(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/restart/" + player + "?pw=" + password;
        serverResponse = getMapping(url);
        return serverResponse;
    }

    public ServerResponse register(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/register/" + player + "?pw=" + password;
        serverResponse = getMapping(url);
        return serverResponse;
    }

    public ServerResponse skip(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/skip/" + player + "?pw=" + password;
        serverResponse = getMapping(url);
        return serverResponse;
    }

    public ServerResponse placeShips(String gameName, int player, String password, ShipCoordinates[] shipCoordinates) {
        String url = serverAddress + "/api/" + gameName + "/placeships/" + player + "?pw=" + password;
        PlaceShipsRequest request = new PlaceShipsRequest(shipCoordinates);
        try {
            ResponseEntity<ServerResponse> response = restTemplate.postForEntity(url, request, ServerResponse.class);
            serverResponse = response.getBody();
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            System.out.println(e.getMessage());
            serverResponse = null;
            return null;
        }
    }

    public ServerResponse shoot(String gameName, int player, String password, int x, int y) {
        String url = serverAddress + "/api/" + gameName + "/shoot/" + player + "?pw=" + password;
        ShootRequest request = new ShootRequest(x, y);
        try {
            ResponseEntity<ServerResponse> response = restTemplate.postForEntity(url, request, ServerResponse.class);
            serverResponse = response.getBody();
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            System.out.println(e.getMessage());
            serverResponse = null;
            return null;
        }
    }

    public GameInfo getGameInfo(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/info/" + player + "?pw=" + password;
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(url, GameInfo.class);
            if(response.getBody() instanceof GameInfo) {
                serverResponse = ServerResponse.SUCCESS;
                return (GameInfo) response.getBody();
            } else if(response.getBody() instanceof ServerResponse) {
                serverResponse = (ServerResponse) response.getBody();
                return null;
            } else {
                serverResponse = ServerResponse.ERROR;
                return null;
            }
        } catch (HttpClientErrorException | ResourceAccessException e) {
            System.out.println(e);
            serverResponse = null;
            return null;
        }
    }

    public boolean myTurn(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/myturn/" + player + "?pw=" + password;
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(url, Object.class);
            if(response.getBody() instanceof Boolean) {
                serverResponse = ServerResponse.SUCCESS;
                return (Boolean) response.getBody();
            } else if(response.getBody() instanceof ServerResponse) {
                serverResponse = (ServerResponse) response.getBody();
                return false;
            } else {
                serverResponse = ServerResponse.ERROR;
                return false;
            }
        } catch (HttpClientErrorException | ResourceAccessException e) {
            System.out.println(e);
            serverResponse = null;
            return false;
        }
    }

    public int getWinner(String gameName, int player, String password) {
        String url = serverAddress + "/api/" + gameName + "/winner/" + player + "?pw=" + password;
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(url, Object.class);
            if(response.getBody() instanceof Integer) {
                serverResponse = ServerResponse.SUCCESS;
                return (Integer) response.getBody();
            } else if(response.getBody() instanceof ServerResponse) {
                serverResponse = (ServerResponse) response.getBody();
                return -1;
            } else {
                serverResponse = ServerResponse.ERROR;
                return -1;
            }
        } catch (HttpClientErrorException | ResourceAccessException e) {
            System.out.println(e);
            serverResponse = null;
            return -1;
        }
    }

    public String getVisual(String gameName, int player, String password, String arg) {
        String url = serverAddress + "/api/" + gameName + "/visual/" + arg + "/" + player + "?pw=" + password;
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(url, Object.class);
            if(response.getBody() instanceof String) {
                serverResponse = ServerResponse.SUCCESS;
                return (String) response.getBody();
            } else if(response.getBody() instanceof ServerResponse) {
                serverResponse = (ServerResponse) response.getBody();
                return null;
            } else {
                serverResponse = ServerResponse.ERROR;
                return null;
            }
        } catch (HttpClientErrorException | ResourceAccessException e) {
            System.out.println(e);
            serverResponse = null;
            return null;
        }
    }

    private ServerResponse getMapping(String url) {
        try {
            ResponseEntity<ServerResponse> response = restTemplate.getForEntity(url, ServerResponse.class);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException e) {
            System.out.println(e);
            return ServerResponse.ERROR;
        }
    }
}
