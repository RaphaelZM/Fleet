package br.com.cinq.volvo.fleet.dto.veichle;

import br.com.cinq.volvo.fleet.dto.Veichle;
import java.io.Serializable;

/**
 *
 * @author rapha
 */
public class Truck extends Veichle implements Serializable {

    private static final long serialVersionUID = 280810130052386823L;

    public Truck() {
        super((byte) 1,"Truck");
    }
}
