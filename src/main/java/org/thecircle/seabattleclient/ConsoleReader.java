package org.thecircle.seabattleclient;

import org.thecircle.seabattleclient.payload.PlaceShipsRequest;
import org.thecircle.seabattleclient.payload.ShipPayload;
import org.thecircle.seabattleclient.payload.ShootRequest;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleReader {

    private boolean read = true;
    private Api api;
    private int player = -1;
    private String password = "";
    private String game = "";
    private List<ShipPayload> ships = new ArrayList<>();

    public ConsoleReader(Api api) {
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

                        case "start":
                            response = api.startGame(lineSplit[1]);
                            if(response.equals("game created")) {
                                game = lineSplit[1];
                            }
                            System.out.println(response);
                            break;
                        case "stopall":
                            if(lineSplit.length == 3) {
                                System.out.println(api.stopAllGames(Integer.parseInt(lineSplit[1]), lineSplit[2]));
                            } else {
                                System.out.println(api.stopAllGames(player, password));
                            }
                            break;
                        case "stop":
                            System.out.println(api.stopGame(lineSplit[1], player, password));
                            break;
                        case "restart":
                            System.out.println(api.restartGame(lineSplit[1], player, password));
                            break;
                        case "register":
                            response = api.register(game, Integer.parseInt(lineSplit[1]), lineSplit[2]);
                            if(response.equals("player registered")) {
                                player = Integer.parseInt(lineSplit[2]);
                                password = lineSplit[3];
                            }
                            System.out.println(response);
                            break;
                        case "myturn":
                            System.out.println(api.myTurn(game, player, password));
                            break;
                        case "skip":
                            System.out.println(api.skip(game, player, password));
                            break;
                        case "addship":
                            ships.add(new ShipPayload(Integer.parseInt(lineSplit[1]), Integer.parseInt(lineSplit[2]),
                                    Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4])));
                            System.out.println("ship added. Use 'placeships' to send them to the server");
                            break;
                        case "clearships":
                            ships = new ArrayList<>();
                            break;
                        case "placeships":
                            PlaceShipsRequest request = new PlaceShipsRequest(ships.toArray(new ShipPayload[0]));
                            System.out.println(api.placeShips(game, player, password, request));
                            break;
                        case "winner":
                            System.out.println(api.getWinner(game, player, password));
                            break;
                        case "shoot":
                            System.out.println(api.shoot(game, player, password,
                                    new ShootRequest(Integer.parseInt(lineSplit[1]), Integer.parseInt(lineSplit[2]))));
                            break;
                        case "visual":
                            System.out.println(api.getVisual(game, player, password, lineSplit[1]));
                            break;
                        case "exit":
                            read = false;
                            break;
                        default:
                            System.out.println("unknown command");
                    }
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("incomplete/wrong input");
                }
            }
        }).start();
    }
}
