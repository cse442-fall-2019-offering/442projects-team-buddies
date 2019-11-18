package demo.app.a442projects_team_buddies;

import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentViewItem {
    private CircleImageView profileImage;
    private String userName;


    public StudentViewItem( String userName){
        //this.profileImage=profileImage;
        this.userName=userName;


    }

    public CircleImageView getStudentPicture() {
        return profileImage;
    }

    public String getStudentName() {
        return userName;
    }

}
