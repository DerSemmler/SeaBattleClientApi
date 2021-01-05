package org.thecircle.seabattleclient;

public class Main {

    public static void main(String[] args) {
        Api api = new Api("https://tomcat.nordbake.de", "user", "pw");

        System.out.println(api.startGame("test"));
        System.out.println(api.startGame("test"));
        System.out.println(api.restartGame("test", 0, "admin"));
        System.out.println(api.stopGame("test", 0, "admin"));
        System.out.println(api.restartGame("test", 0, "admin"));
        System.out.println(api.startGame("test"));
        System.out.println(api.restartGame("test", 0, "admin2"));
        System.out.println(api.stopAllGames(0, "admin"));
        System.out.println(api.restartGame("test", 0, "admin"));

    }
}
