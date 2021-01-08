package org.thecircle.seabattleclientapi;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameInfo {

    private GameStates gameState;
    private int rounds;
    private int currentPlayer;
    private int startPlayer;
    private int winner;
    private int shotsPlayer1;
    private int shotsPlayer2;
    private int hitsPlayer1;
    private int hitsPlayer2;
    private int remainingShipsPlayer1;
    private int remainingShipsPlayer2;

}
