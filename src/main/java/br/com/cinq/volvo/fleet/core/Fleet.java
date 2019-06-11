package br.com.cinq.volvo.fleet.core;

import br.com.cinq.volvo.fleet.errors.VeichleMissingFieldsError;
import br.com.cinq.volvo.fleet.errors.VeichleNullError;
import br.com.cinq.volvo.fleet.errors.DatabaseDeleteError;
import br.com.cinq.volvo.fleet.errors.DatabaseEditError;
import br.com.cinq.volvo.fleet.errors.DatabaseInsertError;
import br.com.cinq.volvo.fleet.dao.DatabaseInterface;
import br.com.cinq.volvo.fleet.dao.MongoDB;
import br.com.cinq.volvo.fleet.dto.Chassis;
import br.com.cinq.volvo.fleet.dto.Veichle;
import br.com.cinq.volvo.fleet.errors.ErrorAbstract;
import br.com.cinq.volvo.fleet.errors.VeichleExistsError;
import java.util.List;

/**
 *
 * @author rapha
 */
public class Fleet {

    private static Fleet fleet = null;
    private final DatabaseInterface db;

    private Fleet() {
        db = new MongoDB();
    }

    public static Fleet getInstance() {
        if (fleet == null) {
            fleet = new Fleet();
        }
        return fleet;
    }

    public synchronized List<String> getTypes() {
        return db.getTypes();
    }
    
    public synchronized ErrorAbstract insert(Veichle veichle) {
        if (veichle == null) {
            return new VeichleNullError();
        } else if (!veichle.isFullField()) {
            return new VeichleMissingFieldsError(veichle);
        } else if (db.contains(veichle.getChassis())) {
            return new VeichleExistsError(veichle);
        } else if (!db.insert(veichle)) {
            return new DatabaseInsertError(veichle);
        }
        return null;
    }

    public synchronized ErrorAbstract edit(Veichle veichle) {
        if (!db.edit(veichle)) {
            return new DatabaseEditError(veichle);
        }
        return null;
    }

    public synchronized ErrorAbstract delete(Veichle veichle) {
        if (!db.delete(veichle)) {
            return new DatabaseDeleteError(veichle);
        }
        return null;
    }

    public synchronized List<Veichle> listAll() {
        return db.listAll();
    }

    public synchronized Veichle find(Chassis chassis) {
        return db.find(chassis);
    }
}
