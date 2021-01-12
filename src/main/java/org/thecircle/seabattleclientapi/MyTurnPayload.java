package org.thecircle.seabattleclientapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class MyTurnPayload {
    private ServerResponse serverResponse;
    private boolean isMyTurn;
}
