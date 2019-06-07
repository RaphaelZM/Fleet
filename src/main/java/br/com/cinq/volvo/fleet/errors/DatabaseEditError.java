package br.com.cinq.volvo.fleet.errors;

import br.com.cinq.volvo.fleet.dto.Veichle;

/**
 *
 * @author rapha
 */
public class DatabaseEditError extends ErrorAbstract {
    private Veichle veichle = null;
    public DatabaseEditError(Veichle veichle) {
        this.veichle = veichle;
    }

    @Override
    public String getErrorMessage() {
        final String message = "There was an error executing edit in the database. Contact technical support.";
        if(veichle != null) {
            return message + "\n" + veichle.toString();
        }
        return message;
    }
    
}
