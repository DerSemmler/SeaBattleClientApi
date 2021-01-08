package org.thecircle.seabattleclientapi;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DebugConsoleAlpha {

    private boolean read = true;
    private Api api;
    private int player = -1;
    private String password = "";
    private String game = "";
    private List<ShipCoordinates> shipCoordinates = new ArrayList<>();

    public DebugConsoleAlpha(Api api) {
        this.api = api;
    }

    public void read() {
        new Thread(() -> {

            Scanner scanner = new Scanner(new InputStreamReader(System.in));

            while (read) {
                while (!scanner.hasNext()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                String line = scanner.nextLine();
                line = line.toLowerCase();
                String[] lineSplit = line.split("\\s+");

                try {
                    String response = "";
                    switch (lineSplit[0]) {
                        case "setgame":
                            game = lineSplit[1];
                            System.out.println("Game set to: " + game);
                            break;
                        case "setplayer":
                            player = Integer.parseInt(lineSplit[1]);
                            System.out.println("Player set to: " + player);
                            break;
                        case "setpassword":
                            password = lineSplit[1];
                            System.out.println("Password set to: " + password);
                            break;

                        case "newgame":
                            ServerResponse response2 = api.newGame(lineSplit[1],
                                    Integer.parseInt(lineSplit[2]), lineSplit[3]);
                            if(response2 == ServerResponse.SUCCESS) {
                                game = lineSplit[1];
                            }
                            break;
                        case "stopall":
                            if(lineSplit.length == 3) {
                                System.out.println(api.stopAllGames(Integer.parseInt(lineSplit[1]), lineSplit[2]));
                            } else {
                                System.out.println(api.stopAllGames(player, password));
                            }
                            break;
                        case "stop":
                            System.out.println(api.stopGame(game, player, password));
                            break;
                        case "restart":
                            System.out.println(api.restartGame(game, player, password));
                            break;
                        case "register":
                            if(!game.isEmpty()) {
                                response = api.register(game, Integer.parseInt(lineSplit[1]), lineSplit[2]);
                                if (response.equals("player registered")) {
                                    player = Integer.parseInt(lineSplit[1]);
                                    password = lineSplit[2];
                                }
                                System.out.println(response);
                            }
                            else {
                                System.out.println("Select a game first. Use 'setgame'");
                            }
                            break;
                        case "myturn":
                            System.out.println(api.myTurn(game, player, password));
                            break;
                        case "skip":
                            System.out.println(api.skip(game, player, password));
                            break;
                        case "addship":
                            shipCoordinates.add(new ShipCoordinates(Integer.parseInt(lineSplit[1]), Integer.parseInt(lineSplit[2]),
                                    Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4])));
                            System.out.println("ship added. Use 'placeships' to send them to the server");
                            break;
                        case "clearships":
                            shipCoordinates = new ArrayList<>();
                            break;
                        case "placeships":
                            System.out.println(api.placeShips(game, player, password, shipCoordinates.toArray(new ShipCoordinates[0])));
                            break;
                        case "winner":
                            System.out.println(api.getWinner(game, player, password));
                            break;
                        case "shoot":
                            System.out.println(api.shoot(game, player, password,
                                    Integer.parseInt(lineSplit[1]), Integer.parseInt(lineSplit[2])));
                            break;
                        case "visual":
                            System.out.println(api.getVisual(game, player, password, lineSplit[1]));
                            break;
                        case "info":
                            printGameInfo(api.getGameInfo(game, player, password));
                            break;
                        case "exit":
                            read = false;
                            break;
                        default:
                            System.out.println("unknown command");
                    }
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("incomplete/wrong input");
                    System.out.println(e);

                }
            }
        }).start();
    }

    private void printGameInfo(GameInfo info) {
        System.out.println("Gamestate: " + info.getGameState());
        System.out.println("Rounds: " + info.getRounds());
        System.out.println("Current player: " + info.getCurrentPlayer());
        System.out.println("Start player: " + info.getStartPlayer());
        System.out.println("Winner: " + info.getWinner());
        System.out.println("Shots PLayer 1: " + info.getShotsPlayer1());
        System.out.println("Shots PLayer 2: " + info.getShotsPlayer2());
        System.out.println("Hits Player 1: " + info.getHitsPlayer1());
        System.out.println("Hits Player 2: " + info.getHitsPlayer2());
        System.out.println("Remaining Ships Player 1: " + info.getRemainingShipsPlayer1());
        System.out.println("Remaining Ships Player 1: " + info.getRemainingShipsPlayer2());
        System.out.println("Boardsize: " + info.getBoardSize());
        System.out.println("Shipset: " + info.getShipSet());
    }
}
