package org.thecircle.seabattleclient.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameInfoPayload {

    private String gameState;
    private int rounds;
    private int currentPlayer;
    private int startPlayer;
    private int shotsPlayer1;
    private int shotsPlayer2;
    private int hitsPlayer1;
    private int hitsPlayer2;
    private int remainingShipsPlayer1;
    private int remainingShipsPlayer2;

}
