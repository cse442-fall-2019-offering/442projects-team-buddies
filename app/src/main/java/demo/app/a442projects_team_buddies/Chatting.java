package demo.app.a442projects_team_buddies;

import com.parse.ParseClassName;
import com.parse.ParseObject;


@ParseClassName("Message")
public class Chatting extends ParseObject {

    public static final String USER_ID_KEY = "userId";
    public static final String BODY_KEY = "body";
    public static final String RECEIVER_ID_KEY = "receiverId";
    public String getUserId() {
        return getString(USER_ID_KEY);
    }

    public String getBody() {
        return getString(BODY_KEY);
    }

    public String getReceiverId() {
        return getString(RECEIVER_ID_KEY);
    }


    public void setUserId(String userId) {
        put(USER_ID_KEY, userId);
    }

    public void setBody(String body) {
        put(BODY_KEY, body);
    }

    public void setReceiverId(String receiverId) {
        put(RECEIVER_ID_KEY, receiverId);
    }
}