
package Services;

import Model.UserAuth;
import Utils.JwTokenHelper;
import Utils.MongoDBHelper;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bson.Document;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author KingMathew
 */
@Path("/auth")
public class Auth {

    MongoDBHelper mongoDBHelper = new MongoDBHelper();

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public String validateUser(UserAuth user) throws JSONException {
        JSONObject response = new JSONObject();
        try {
            MongoCollection<Document> collection = mongoDBHelper.getDataBase().getCollection("users");
            Document cursor = collection.find(and(eq("user", user.getUser()), eq("password", user.getPass())))
                    .projection(fields(include("user"), excludeId()))
                    .first();
            String privateKey = JwTokenHelper.getInstance().generatePrivateKey(user.getUser(), user.getPass());
            JSONObject data = new JSONObject(cursor.toJson());
            response.put("success", true);
            response.put("privateKey", privateKey);
            response.put("data", data);
            return response.toString();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        response.put("success", false);
        response.put("message", "Credenciales incorrectas.");
        return response.toString();

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getUsers() {
        ArrayList<String> array = new ArrayList<>();
        try {
            MongoCollection<Document> collection = mongoDBHelper.getDataBase().getCollection("users");
            for (Document cur : collection.find().projection(fields(include("user"), excludeId()))) {
                array.add(cur.toJson());
                System.out.println(cur.toJson());
            }
            return array.toString();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return "Sin Usuarios";
    }

}
