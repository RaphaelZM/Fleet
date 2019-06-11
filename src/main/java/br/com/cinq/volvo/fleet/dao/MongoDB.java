package br.com.cinq.volvo.fleet.dao;

import br.com.cinq.volvo.fleet.dto.Chassis;
import br.com.cinq.volvo.fleet.dto.Veichle;
import br.com.cinq.volvo.fleet.dto.veichle.Bus;
import br.com.cinq.volvo.fleet.dto.veichle.Car;
import br.com.cinq.volvo.fleet.dto.veichle.Truck;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author rapha
 */
public class MongoDB implements DatabaseInterface {

    private final String dbURL = "mongodb://localhost:27017";
    private MongoClient mongoClient = null;

    private MongoCollection getCollection() {
        mongoClient = new MongoClient(new MongoClientURI(dbURL));
        MongoDatabase database = mongoClient.getDatabase("Cinq");
        MongoCollection collection = database.getCollection("Fleet");
        return collection;
    }

    @Override
    public boolean contains(Chassis chassis) {
        return find(chassis) != null;
    }

    @Override
    public boolean insert(Veichle veichle) {
        try {
            getCollection().insertOne(Document.parse(veichle.toString()));
            mongoClient.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean edit(Veichle veichle) {
        try {
            getCollection().updateOne(Filters.eq("chassis", Document.parse(veichle.getChassis().toString())), new Document("$set", new Document("color", veichle.getColor())));
            mongoClient.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Veichle veichle) {
        try {
            getCollection().deleteOne(Filters.eq("chassis", Document.parse(veichle.getChassis().toString())));
            mongoClient.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Veichle> listAll() {
        List<Veichle> veichles = new ArrayList<>();
        FindIterable<Document> documents = getCollection().find(Filters.exists("chassis"));
        for (Document d : documents) {
            System.out.println(d.toJson());
            veichles.add(getVeichle(d));
        }
        return veichles;
    }

    @Override
    public Veichle find(Chassis chassis) {
        try {
            Bson filter = Filters.all("chassis",Filters.and(
        Filters.eq("number",chassis.getNumber()),
                Filters.eq("series", chassis.getSeries())));
            FindIterable<Document> documents = getCollection().find(filter);
            for (Document d : documents) {
                Veichle v = getVeichle(d);
                mongoClient.close();
                return v;
            }
            mongoClient.close();
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    public List<String> getTypes() {
        FindIterable<Document> types = getCollection().find(Filters.exists("types"));
        List<String> results = new ArrayList<>();
        for(Document type: types) {
            results.add(type.getString("types"));
        }
        mongoClient.close();
        return results;
    }

    private Veichle getVeichle(Document d) {
        try {
            Veichle v = null;
            switch (d.getString("type").toLowerCase()) {
                case "car":
                    v = new Car();
                    break;
                case "bus":
                    v = new Bus();
                    break;
                case "truck":
                    v = new Truck();
                    break;
            }
            
            v.setColor(d.getString("color"));
            Document chassis = (Document) d.get("chassis");
            v.getChassis().setSeries(chassis.getString("series"));
            v.getChassis().setNumber(chassis.getLong("number"));
            return v;
        } catch (Exception e) {
            return null;
        }
    }

}
