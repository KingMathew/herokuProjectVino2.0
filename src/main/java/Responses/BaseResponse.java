package Responses;

import com.google.gson.JsonObject;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseResponse {

    public static final int FAILURE = 0;
    public static final int SUCCESS = 1;

    private String message;
    private int status;
    private JsonObject data;

    public BaseResponse() {
    }

    public BaseResponse(String message, int status, JsonObject data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

}
