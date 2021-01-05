package org.thecircle.seabattleclient;

import lombok.Getter;

import java.io.*;
import java.util.Properties;

@Getter
public class PropertiesReader {

    private String serverAddress = "url";
    private String password = "pASsWD123";

    public PropertiesReader(){
        Properties props = new Properties();
        try (InputStream inStream = new FileInputStream("config.properties")) {
            props.load(inStream);
            try (OutputStream output = new FileOutputStream("config.properties")) {

                if (props.containsKey("serverAddress")) serverAddress = props.getProperty("serverAddress");
                else props.setProperty("serverAddress", serverAddress);

                if (props.containsKey("password")) password = props.getProperty("password");
                else props.setProperty("password", password);

                props.store(output, null);

            } catch (IOException io) {
                io.printStackTrace();
            }

        } catch (IOException e) {
            createNewPropertiesFile();
        }
    }

    private void createNewPropertiesFile() {
        try (OutputStream output = new FileOutputStream("config.properties")) {
            Properties props = new Properties();

            props.setProperty("serverAddress", serverAddress);
            props.setProperty("password", password);

            props.store(output, null);
            System.out.println("config.properties created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
