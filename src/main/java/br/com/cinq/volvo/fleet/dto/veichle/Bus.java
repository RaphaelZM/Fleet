package br.com.cinq.volvo.fleet.dto.veichle;

import br.com.cinq.volvo.fleet.dto.Veichle;

/**
 *
 * @author rapha
 */
public class Bus extends Veichle {
    
    private static final long serialVersionUID = 927260593309844623L;
    
    public Bus() {
        super((byte) 42, "Bus");
    }
}
