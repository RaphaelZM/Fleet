package br.com.cinq.volvo.fleet.errors;

import java.io.Serializable;

/**
 *
 * @author rapha
 */
public abstract class ErrorAbstract implements Serializable {
    private static final long serialVersionUID = -3401801190492659565L;
    
    public abstract String getErrorMessage();
}
