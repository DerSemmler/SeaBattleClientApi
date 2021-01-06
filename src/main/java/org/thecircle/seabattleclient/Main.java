package org.thecircle.seabattleclient;

public class Main {

    public static void main(String[] args) {

        PropertiesReader propertiesReader = new PropertiesReader();
        String address = propertiesReader.getServerAddress();
        String userName = propertiesReader.getBasicAuthUser();
        String password = propertiesReader.getBasicAuthPw();


        Api api = new Api(address, userName, password);

        ConsoleReader reader = new ConsoleReader(api);
        reader.read();

    }
}
