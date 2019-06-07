package br.com.cinq.volvo.fleet.view.managedbeans;

import br.com.cinq.volvo.fleet.core.Fleet;
import br.com.cinq.volvo.fleet.dto.Chassis;
import br.com.cinq.volvo.fleet.dto.Veichle;
import br.com.cinq.volvo.fleet.dto.veichle.Bus;
import br.com.cinq.volvo.fleet.dto.veichle.Car;
import br.com.cinq.volvo.fleet.dto.veichle.Truck;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author rapha
 */
@ManagedBean(name="tableMB")
@SessionScoped
public class TableManagedBean implements Serializable {

    private static final long serialVersionUID = -2805889865892785199L;
    private Veichle selectedVeichle;
    private List<Veichle> veichles;
    private String type;
    private Chassis chassis;
    private String color;
    private boolean renderImage;

    public TableManagedBean() {
        renderImage = false;
        chassis = new Chassis();
        color = "";
        type = "";
    }
    
    @PostConstruct
    public void init() {
        veichles = Fleet.getInstance().listAll();
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
    
    

    public List<Veichle> getVeichles() {
        return veichles;
    }

    public void setVeichles(List<Veichle> veichles) {
        this.veichles = veichles;
    }

    public Veichle getSelectedVeichle() {
        return selectedVeichle;
    }
    
    public List<String> getTypes() {
        return Fleet.getInstance().getTypes();
    }

    public boolean isRenderImage() {
        if(selectedVeichle != null) {
            this.renderImage = selectedVeichle.isFullField();
        } else {
            this.renderImage = false;
        }
        return this.renderImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRenderImage(boolean renderImage) {
        this.renderImage = renderImage;
    }
    
    public void setSelectedVeichle(Veichle selectedVeichle) {
        this.selectedVeichle = selectedVeichle;
    }
    
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Veichle Selected", ((Veichle) event.getObject()).getChassis().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public TableManagedBean(List<Veichle> table) {
        this.veichles = table;
    }
    
    public void insert() {
        Veichle v;
        if(type.equalsIgnoreCase("car")) {
            v = new Car();
        } else if (type.equalsIgnoreCase("truck")) {
            v = new Truck();
        } else if (type.equalsIgnoreCase("bus")) {
            v = new Bus();
        } else {
            return;
        }
        if(!color.isEmpty()) {
            v.setColor(color);
        } else {
            
        }
        if(chassis.isFullField()) {
            v.setChassis(chassis);
        } else {
            
        }
        Fleet.getInstance().insert(v);
    }
}
