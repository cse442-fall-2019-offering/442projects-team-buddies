package demo.app.a442projects_team_buddies;

import android.graphics.Bitmap;

public class ChatViewItem {

    public String message;
    public String status;



    public ChatViewItem(String message, String status){
        //this.profileImage=profileImage;
        this.status= status;
        this.message= message;



    }



    public String getMessage() {
        return message;
    }
    public String getStatus(){return status;}

}
