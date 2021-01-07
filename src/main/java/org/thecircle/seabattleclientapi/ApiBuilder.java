package org.thecircle.seabattleclientapi;

import lombok.Getter;

public class ApiBuilder {
    @Getter
    private Api api;

    @Getter
    private DebugConsoleAlpha console;

    private PropertiesReader propertiesReader;

    public ApiBuilder() {
        propertiesReader = new PropertiesReader();
        System.out.println("Customize config.properties before building.");
    }

    public ApiBuilder(String configPath) {
        propertiesReader = new PropertiesReader(configPath);
        System.out.println("Customize config.properties before building.");
    }

    public Api build() {

        api = new Api(propertiesReader.getServerAddress(), propertiesReader.getBasicAuthUser(),
                propertiesReader.getBasicAuthPw());
        console = new DebugConsoleAlpha(api);
        return api;
    }

}
