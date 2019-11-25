package demo.app.a442projects_team_buddies;

import android.graphics.Bitmap;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentViewItem {
    private Bitmap profileImage;
    private String userName;



    public StudentViewItem( String userName,Bitmap bitmap){
        //this.profileImage=profileImage;
        this.userName=userName;
        this.profileImage= bitmap;


    }

    public Bitmap getStudentPicture() {
        return profileImage;
    }

    public String getStudentName() {
        return userName;
    }

}
