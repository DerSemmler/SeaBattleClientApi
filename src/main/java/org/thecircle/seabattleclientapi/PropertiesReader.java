package org.thecircle.seabattleclientapi;

import lombok.Getter;

import java.io.*;
import java.util.Properties;

@Getter
class PropertiesReader {

    private String serverAddress = "url";
    private String basicAuthUser = "userName";
    private String basicAuthPw = "pASsWD123";

    private String configPath = "config.properties";

    public PropertiesReader(){
        build();
    }

    public PropertiesReader(String path) {
        configPath = path + configPath;
        build();
    }

    private void build() {
        Properties props = new Properties();
        try (InputStream inStream = new FileInputStream(configPath)) {
            props.load(inStream);
            try (OutputStream output = new FileOutputStream(configPath)) {

                if (props.containsKey("serverAddress")) serverAddress = props.getProperty("serverAddress");
                else props.setProperty("serverAddress", serverAddress);

                if (props.containsKey("userName")) basicAuthUser = props.getProperty("userName");
                else props.setProperty("userName", basicAuthUser);

                if (props.containsKey("password")) basicAuthPw = props.getProperty("password");
                else props.setProperty("password", basicAuthPw);

                props.store(output, null);

            } catch (IOException io) {
                io.printStackTrace();
            }

        } catch (IOException e) {
            createNewPropertiesFile();
        }
    }

    private void createNewPropertiesFile() {
        try (OutputStream output = new FileOutputStream(configPath)) {
            Properties props = new Properties();

            props.setProperty("serverAddress", serverAddress);
            props.setProperty("password", basicAuthPw);
            props.setProperty("userName", basicAuthUser);

            props.store(output, null);
            System.out.println("config.properties created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
