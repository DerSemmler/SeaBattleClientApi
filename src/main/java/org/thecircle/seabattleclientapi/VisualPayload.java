package org.thecircle.seabattleclientapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

public class VisualPayload {
    private ServerResponse serverResponse;
    private String visual;
}


