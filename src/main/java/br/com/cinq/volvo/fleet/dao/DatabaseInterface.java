package br.com.cinq.volvo.fleet.dao;

import br.com.cinq.volvo.fleet.dto.Chassis;
import br.com.cinq.volvo.fleet.dto.Veichle;
import java.util.List;

/**
 *
 * @author rapha
 */
public interface DatabaseInterface {

    public boolean contains(Chassis chassis);

    public boolean insert(Veichle veichle);

    public boolean edit(Veichle veichle);

    public boolean delete(Veichle veichle);

    public List<Veichle> listAll();

    public Veichle find(Chassis chassis);

    public List<String> getTypes();
    
}
