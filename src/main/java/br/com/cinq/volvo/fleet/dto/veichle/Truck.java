package br.com.cinq.volvo.fleet.dto.veichle;

import br.com.cinq.volvo.fleet.dto.Veichle;

/**
 *
 * @author rapha
 */
public class Truck extends Veichle {

    private static final long serialVersionUID = 280810130052386823L;

    public Truck() {
        super((byte) 1,"Truck");
    }
}
