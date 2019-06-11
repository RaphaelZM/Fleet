package br.com.cinq.volvo.fleet.dto;

import java.io.Serializable;

/**
 *
 * @author Raphael Zagonel Moletta
 */
public class Veichle implements Serializable {
    private static final long serialVersionUID = 3396346766426946925L;
    private Chassis chassis;
    private String color;
    private final byte numberOfPassengers;
    private final String type;

    public Veichle(byte numberOfPassengers, String type) {
        this.numberOfPassengers = numberOfPassengers;
        this.type = type;
        this.chassis = new Chassis();
        this.color = "";
    }
    
    public Chassis getChassis() {
        return chassis;
    }

    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public byte getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public String getType() {
        return type;

    }
    
    public boolean isFullField() {
        return chassis.isFullField() && !this.color.isEmpty();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{chassis:");
        sb.append(this.getChassis().toString());
        sb.append(",numberOfPassengers:");
        sb.append(this.numberOfPassengers);
        sb.append(",type:'");
        sb.append(this.type);
        sb.append("', color:'");
        sb.append(this.color);
        sb.append("'}");
        return sb.toString();
    }
}
