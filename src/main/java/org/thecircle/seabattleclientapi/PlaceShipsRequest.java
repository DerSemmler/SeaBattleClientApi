package org.thecircle.seabattleclientapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceShipsRequest {

    private ShipCoordinates[] ships;

}
