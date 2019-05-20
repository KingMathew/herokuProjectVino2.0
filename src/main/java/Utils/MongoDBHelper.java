package Utils;

import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;

public class MongoDBHelper {

    private final static String HOST = "54.242.180.153";
    private final static int PORT = 27017;

    public MongoDatabase getDataBase() {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase database = mongoClient.getDatabase("vinoDB");
            return database;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

}
