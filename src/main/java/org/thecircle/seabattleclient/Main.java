package org.thecircle.seabattleclient;

public class Main {

    public static void main(String[] args) {

        PropertiesReader propertiesReader = new PropertiesReader();
        String address = propertiesReader.getServerAddress();
        String userName = propertiesReader.getBasicAuthUser();
        String password = propertiesReader.getBasicAuthPw();


        Api api = new Api(address, userName, password);

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
