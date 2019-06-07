package br.com.cinq.volvo.fleet.core;

import br.com.cinq.volvo.fleet.dto.Chassis;
import br.com.cinq.volvo.fleet.dto.Veichle;
import br.com.cinq.volvo.fleet.dto.veichle.*;
import br.com.cinq.volvo.fleet.errors.ErrorAbstract;
import br.com.cinq.volvo.fleet.errors.VeichleExistsError;
import br.com.cinq.volvo.fleet.errors.VeichleMissingFieldsError;
import br.com.cinq.volvo.fleet.errors.VeichleNullError;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rapha
 */
public class FleetTest {
    
    public FleetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class Fleet.
     */
    @Test
    public void testGetInstance() {
        System.out.println("---------------- TEST ----------------\n[getInstance]");
        Fleet expResult = null;
        Fleet result = Fleet.getInstance();
        
        System.out.println("\tTEST: Not null");
        assertNotEquals(expResult, result);
        System.out.println("\t\tOK");
        Fleet result2 = Fleet.getInstance();
        System.out.println("\tTEST: Same instance");
        assertEquals(result2, result);
        System.out.println("\t\tOK");
    }

    /**
     * Te st of insert method, of class Fleet.
     */
    @Test
    public void testInsertNull() {
        System.out.println("---------------- TEST ----------------\n[insert]");
        
        System.out.println("\tTEST: Null insertion ");
        Veichle veichle = null;
        ErrorAbstract result = Fleet.getInstance().insert(veichle);
        
        assertTrue(result instanceof VeichleNullError);
        System.out.println("\t\tOK");
    }

    /**
     * Te st of insert method, of class Fleet.
     */
    @Test
    public void testInsertEmpty() {
        System.out.println("---------------- TEST ----------------\n[insert]");
        
        System.out.println("\tTEST: Fullfield data (full empty)");
        Veichle veichle = new Car();
        ErrorAbstract result = Fleet.getInstance().insert(veichle);
        
        assertTrue(result instanceof VeichleMissingFieldsError);
        System.out.println("\t\tOK");
    }

    /**
     * Te st of insert method, of class Fleet.
     */
    @Test
    public void testInsertNoColor() {
        System.out.println("---------------- TEST ----------------\n[insert]");
        
        System.out.println("\tTEST: missing color");
        Veichle veichle = new Car();
        veichle.getChassis().setNumber(100);
        veichle.getChassis().setSeries("asd");
        ErrorAbstract result = Fleet.getInstance().insert(veichle);
        
        assertTrue(result instanceof VeichleMissingFieldsError);
        System.out.println("\t\tOK");
    }
    
    /**
     * Te st of insert method, of class Fleet.
     */
    @Test
    public void testInsertOnlyColor() {
        System.out.println("---------------- TEST ----------------\n[insert]");
        
        System.out.println("\tTEST: only color");
        Veichle veichle = new Car();
        veichle.setColor("RED");
        ErrorAbstract result = Fleet.getInstance().insert(veichle);
        
        assertTrue(result instanceof VeichleMissingFieldsError);
        System.out.println("\t\tOK");
    }

    /**
     * Te st of insert method, of class Fleet.
     */
    @Test
    public void testInsertOnlySeries() {
        System.out.println("---------------- TEST ----------------\n[insert]");
        
        System.out.println("\tTEST: only series");
        Veichle veichle = new Car();
        veichle.getChassis().setSeries("asd");
        ErrorAbstract result = Fleet.getInstance().insert(veichle);
        
        assertTrue(result instanceof VeichleMissingFieldsError);
        System.out.println("\t\tOK");
    }
    
    /**
     * Te st of insert method, of class Fleet.
     */
    @Test
    public void testInsertOnlyNumber() {
        System.out.println("---------------- TEST ----------------\n[insert]");
        
        System.out.println("\tTEST: only Number");
        Veichle veichle = new Car();
        veichle.getChassis().setNumber(100);
        ErrorAbstract result = Fleet.getInstance().insert(veichle);
        
        assertTrue(result instanceof VeichleMissingFieldsError);
        System.out.println("\t\tOK");
    }
    
    /**
     * Te st of insert method, of class Fleet.
     */
    @Test
    public void testInsertAndExists() {
        System.out.println("---------------- TEST ----------------\n[insert]");
        
        System.out.println("\tTEST: correct insert");
        Veichle veichle = new Car();
        veichle.getChassis().setSeries("asdf");
        veichle.getChassis().setNumber(100);
        veichle.setColor("black");
        ErrorAbstract result = Fleet.getInstance().insert(veichle);
        assertTrue(result == null);
        System.out.println("\t\tOK");
        
        System.out.println("\tTEST: duplicate insert");
        Veichle veichle2 = new Car();
        veichle2.getChassis().setSeries("asdf");
        veichle2.getChassis().setNumber(100);
        veichle2.setColor("black");
        result = Fleet.getInstance().insert(veichle2);
        assertTrue(result instanceof VeichleExistsError);
        System.out.println("\t\tOK");
    }
    
    /**
     * Test of edit method, of class Fleet.
     */
    
    @Test
    public void testEdit() {
        System.out.println("---------------- TEST ----------------\n[edit]");
        Veichle veichle = new Bus();
        veichle.getChassis().setNumber(100);
        veichle.getChassis().setSeries("asd");
        veichle.setColor("RED");
        ErrorAbstract result = Fleet.getInstance().insert(veichle);
        assertTrue(result == null);
        veichle = Fleet.getInstance().find(new Chassis("asd", 100));
        veichle.setColor("Black");
        Fleet.getInstance().edit(veichle);
        veichle = Fleet.getInstance().find(new Chassis("asd", 100));
        assertTrue(veichle.getColor().equals("Black"));
        result = Fleet.getInstance().delete(veichle);
        assertTrue(result == null);
        System.out.println("\t\tOK");
    }
   
    /**
     * Test of listAll method, of class Fleet.
     */
    
    @Test
    public void testListAll() {
        System.out.println("---------------- TEST ----------------\n[listAll]");
        List<Veichle> result = Fleet.getInstance().listAll();
        Veichle bus = new Bus();
        bus.setChassis(new Chassis("asd", 100));
        bus.setColor("Red");
        Fleet.getInstance().insert(bus);
        
        Veichle truck = new Truck();
        truck.setChassis(new Chassis("asf", 101));
        truck.setColor("Brown");
        Fleet.getInstance().insert(truck);
        
        Veichle car = new Car();
        car.setChassis(new Chassis("asg",102));
        car.setColor("White");
        Fleet.getInstance().insert(car);
        
        Veichle car2 = new Car();
        car2.setChassis(new Chassis("ash",103));
        car2.setColor("Black");
        Fleet.getInstance().insert(car2);
        
        for(Veichle v : result) {
            System.out.println(v.toString());
        }
        assertTrue(result.size() == 4);
        
        ErrorAbstract e = Fleet.getInstance().delete(car);
        assertTrue(e == null);
        e = Fleet.getInstance().delete(car2);
        assertTrue(e == null);
        e = Fleet.getInstance().delete(bus);
        assertTrue(e == null);
        e = Fleet.getInstance().delete(truck);
        assertTrue(e == null);
        System.out.println("\t\tOK");
    }
    /**
     * Test of find method, of class Fleet.
     */
    @Test
    public void testFind() {
        System.out.println("---------------- TEST ----------------\n[find]");
        Veichle truck = new Truck();
        truck.setChassis(new Chassis("asf", 101));
        truck.setColor("Brown");
        Fleet.getInstance().insert(truck);
        Veichle v = Fleet.getInstance().find(new Chassis("asf", 101));
        assertTrue(v.getColor().equals("Brown")
        && v.getChassis().getNumber() == 101
        && v.getChassis().getSeries().equals("asf"));
        v = Fleet.getInstance().find(new Chassis("asf", 102));
        assertTrue(v == null);
        System.out.println("\t\tOK");
    }
}
