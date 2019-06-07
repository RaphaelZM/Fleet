package br.com.cinq.volvo.fleet.errors;

import br.com.cinq.volvo.fleet.dto.Veichle;

/**
 *
 * @author rapha
 */
public class VeichleExistsError extends ErrorAbstract {
    private Veichle veichle = null;
    
    public VeichleExistsError(Veichle veichle) {
        this.veichle = veichle;
    }

    @Override
    public String getErrorMessage() {
        final String message = "This veichle already exists in database.";
        if(veichle != null) {
            return message + "\n" + veichle.toString();
        }
        return message;
    }
    
}
