package br.com.cinq.volvo.fleet.dto.veichle;

import br.com.cinq.volvo.fleet.dto.Veichle;

/**
 *
 * @author rapha
 */
public class Car extends Veichle {

    private static final long serialVersionUID = -7279308836257381872L;

    public Car() {
        super((byte) 4, "Car");
    }
}
