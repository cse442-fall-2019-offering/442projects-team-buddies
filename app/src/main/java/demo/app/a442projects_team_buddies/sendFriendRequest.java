package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class sendFriendRequest extends AppCompatActivity {
    // get username, id , major , description , profile
    private TextView user_name, user_email, user_desc, user_major;
    private CircleImageView user_profile;
    private Button add;
    ParseObject cur_user;
//    ParseQuery<ParseObject> classmate_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profileview);




        // Retrieve the data from the db
//        classmate_info= ParseQuery.getQuery("User").g;
//        classmate_info.whereEqualTo(getIntent().getExtras().getString("username"), ParseUser.getCurrentUser().getUsername());

        cur_user = cur_user.getParseUser(getIntent().getExtras().getString("username"));

        user_name = findViewById(R.id.classmate_name);
        user_name.setText(cur_user.getString("username"));


        user_email = findViewById(R.id.classmate_email);
        user_email.setText(cur_user.getString("email"));

        user_desc = findViewById(R.id.classmate_desc);
        user_desc.setText(cur_user.getString("personalDescription"));


        user_major = findViewById(R.id.classmate_major);
        user_major.setText(cur_user.getString("major"));


        user_profile = findViewById(R.id.classmate_img);
        try {

            ParseFile file = (ParseFile) cur_user.get("Profile_Image");

            file.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {

                    if (e == null && data != null) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                        user_profile.setImageBitmap(bitmap);
                    } else {
                        // do nothing, keep default profile image
                    }
                }
            });

        }
        catch (Exception ex)
        {
           // ..... throw exception ???
        }


        // Send out the friend request
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Send out the friend request via backend



                // Then, take user back to the course member list, after prior action is completed

                Intent backToMemberList= new Intent(sendFriendRequest.this,SearchActivity.class);
                startActivity(backToMemberList);
            }
        });

    }
}
