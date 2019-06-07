package br.com.cinq.volvo.fleet.errors;

import br.com.cinq.volvo.fleet.dto.Veichle;

/**
 *
 * @author rapha
 */
public class VeichleMissingFieldsError extends ErrorAbstract {

    private static final long serialVersionUID = 286804018186280113L;

    public VeichleMissingFieldsError(Veichle veichle) {
    }

    @Override
    public String getErrorMessage() {
        return null;
    }

}
