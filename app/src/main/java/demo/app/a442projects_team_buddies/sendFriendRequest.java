package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class sendFriendRequest extends AppCompatActivity {
    // get username, id , major , description , profile
    private TextView user_name, user_email, user_desc, user_major;
    private CircleImageView user_profile;
    private Button add;
    private TextView user_loc;

//    ParseQuery<ParseObject> classmate_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profileview);

        Intent intent= getIntent();

        final String username= intent.getStringExtra("username");






        user_loc= findViewById(R.id.classmate_loc);
        add= findViewById(R.id.btn_sendFriendRequest);

        user_name = findViewById(R.id.classmate_name);
        user_email = findViewById(R.id.classmate_email);
        user_desc = findViewById(R.id.classmate_desc);
        user_major = findViewById(R.id.classmate_major);
        user_profile = findViewById(R.id.classmate_img);

        final ParseQuery<ParseUser> user = ParseUser.getQuery();

        user.whereEqualTo("username",username);

        user.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null && objects.size() > 0) {
                    for (final ParseUser user1 : objects) {

                        String loc= "<b>"+"Favourite study Location: "+"</b>" +user1.getString("studyLocation") ;
                        String maj="<b>"+"Major: "+"</b>" +user1.getString("major");
                        String des="<b>"+"Description: "+"</b>" +user1.getString("personalDescription");
                        String email="<b>"+"Email: "+"</b>" + user1.getEmail();

                        user_loc.setText(Html.fromHtml(loc));
                        user_major.setText(Html.fromHtml(maj));
                        user_desc.setText(Html.fromHtml(des));
                        user_name.setText(user1.getString("name"));
                        user_email.setText(Html.fromHtml(email));

                        try {


                            ParseFile file = (ParseFile) user1.get("Profile_Image");


                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {


                                    if (e == null && data != null) {
                                        if (e == null && data != null) {
                                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                                            user_profile.setImageBitmap(bitmap);
                                        }
                                    }

                                }
                            });

                        }
                        catch (Exception ex)
                        {


                        }
                    }

                }

            }
        });



        // Send out the friend request
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Send out the friend request via backend

                Toast.makeText(getBaseContext(),"add button pressed", Toast.LENGTH_LONG).show();


                // Then, take user back to the course member list, after prior action is completed


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
