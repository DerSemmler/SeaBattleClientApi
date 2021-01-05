package org.thecircle.seabattleclient;

public class Main {

    public static void main(String[] args) {
        System.out.println(new Api("http://localhost:8080").startGame("hi"));
    }
}
