package br.com.cinq.volvo.fleet.errors;

import br.com.cinq.volvo.fleet.dto.Veichle;

/**
 *
 * @author rapha
 */
public class DatabaseDeleteError extends ErrorAbstract {

    private static final long serialVersionUID = -6314912567962713922L;

    private Veichle veichle = null;

    public DatabaseDeleteError(Veichle veichle) {
        this.veichle = veichle;
    }

    @Override
    public String getErrorMessage() {
        final String message = "There was an error executing delete in the database. Contact technical support.";
        if (veichle != null) {
            return message + "\n" + veichle.toString();
        }
        return message;
    }

}
