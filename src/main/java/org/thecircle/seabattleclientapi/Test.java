package org.thecircle.seabattleclientapi;

class Test {
    public static void main(String[] args) {
        ApiBuilder apiBuilder = new ApiBuilder();
        Api api = apiBuilder.build();
        api.deactivateBasicAuthentication();
        DebugConsoleAlpha console = apiBuilder.getConsole();
        console.read();
    }
}
