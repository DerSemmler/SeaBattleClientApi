package org.thecircle.seabattleclientapi;

public class Test {
    public static void main(String[] args) {
        ApiBuilder apiBuilder = new ApiBuilder();
        Api api = apiBuilder.build();
        DebugConsoleAlpha console = apiBuilder.getConsole();
        console.read();
    }
}
