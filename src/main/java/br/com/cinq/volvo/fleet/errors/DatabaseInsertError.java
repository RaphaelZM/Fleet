/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cinq.volvo.fleet.errors;

import br.com.cinq.volvo.fleet.dto.Veichle;

/**
 *
 * @author rapha
 */
public class DatabaseInsertError extends ErrorAbstract {
    private Veichle veichle = null;
    public DatabaseInsertError(Veichle veichle) {
        this.veichle = veichle;
    }

    @Override
    public String getErrorMessage() {
        final String message = "There was an error executing insert in the database. Contact technical support.";
        if(veichle != null) {
            return message + "\n" + veichle.toString();
        }
        return message;
    }
    
}
