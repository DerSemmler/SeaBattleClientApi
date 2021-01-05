package org.thecircle.seabattleclient;

import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleReader {

    private boolean read = true;

    public void reader() {
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

                String line = scanner.next();
                String lineSplit = line.split("\\s+")[0];

                switch (lineSplit) {
                    case "newgame":
                    case "stopall":
                    case "stop":
                    case "restart":
                    case "register":
                    case "info":
                    case "myturn":
                    case "skip":
                    case "placeships":
                    case "winner":
                    case "shoot":
                    case "visual": System.out.println("test"); break;
                    case "exit": read = false; break;
                }
            }
        }).start();
    }
}
