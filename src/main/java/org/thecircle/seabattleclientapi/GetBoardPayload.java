package org.thecircle.seabattleclientapi;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class GetBoardPayload {

    ServerResponse serverResponse;
    int[][] board;

}
