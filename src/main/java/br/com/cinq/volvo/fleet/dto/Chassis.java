package br.com.cinq.volvo.fleet.dto;

import java.io.Serializable;

/**
 *
 * @author rapha
 */
public class Chassis implements Serializable {
    private static final long serialVersionUID = -159000714366193248L;
    private String series;
    /*Subtyping the Unsigned Integer type to Long because Java does not contain
    Unsigned types in this way to meet the positive maximum value requirement is
    necessary to extend the size of bytes
    */
    private long number;

    public Chassis() {
        this.number = 0;
        this.series = "";
    }

    public Chassis(String series, long number) {
        this.series = series;
        this.number = number;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long Number) {
        this.number = Number;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{number:NumberLong(");
        sb.append(this.number);
        sb.append("),series:'");
        sb.append(this.series);
        sb.append("'}");
        return sb.toString();
    }

    public boolean isFullField() {
        return !this.series.isEmpty() && this.number > 0;
    }
}
