package br.com.cinq.volvo.fleet.dao;

import br.com.cinq.volvo.fleet.dto.Chassis;
import br.com.cinq.volvo.fleet.dto.Veichle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rapha
 */
public class ListDB implements DatabaseInterface {

    private List<Veichle> fleet;

    public ListDB() {
        this.fleet = new ArrayList<>();
    }

    @Override
    public boolean contains(Chassis chassis) {
        for (Veichle veichle : fleet) {
            if (veichle.getChassis().getNumber() == chassis.getNumber()
                    && veichle.getChassis().getSeries().equalsIgnoreCase(chassis.getSeries())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insert(Veichle veichle) {
        fleet.add(veichle);
        return true;
    }

    @Override
    public boolean edit(Veichle veichle) {
        for (Veichle v : fleet) {
            if (veichle.getChassis().getNumber() == v.getChassis().getNumber()
                    && veichle.getChassis().getSeries().equalsIgnoreCase(v.getChassis().getSeries())) {
                v.setColor(veichle.getColor());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Veichle veichle) {
        for (Veichle v : fleet) {
            if (veichle.getChassis().getNumber() == v.getChassis().getNumber()
                    && veichle.getChassis().getSeries().equalsIgnoreCase(v.getChassis().getSeries())) {
                fleet.remove(v);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Veichle> listAll() {
        return fleet;
    }

    @Override
    public Veichle find(Chassis chassis) {
        for (Veichle v : fleet) {
            if (chassis.getNumber() == v.getChassis().getNumber()
                    && chassis.getSeries().equalsIgnoreCase(v.getChassis().getSeries())) {
                return v;
            }
        }
        return null;
    }

    @Override
    public List<String> getTypes() {
        List<String> list = new ArrayList<>();
        list.add("Bus");
        list.add("Car");
        list.add("Truck");
        return list;
    }

}
