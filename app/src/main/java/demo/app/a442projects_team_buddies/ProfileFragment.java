package demo.app.a442projects_team_buddies;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {


    CircleImageView profileImage;
    TextView location, major, description;
    TextView userName;
    TextView userEmail;
    Button editButton;

    ParseUser currentUser;
    //static String personPhoto;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View inflater1=inflater.inflate(R.layout.profile_page,container,false);

        location = inflater1.findViewById(R.id.textViewProfileLocation);
        major = inflater1.findViewById(R.id.textViewProfileMajor);
        description = inflater1.findViewById(R.id.textViewProfileDescription);
        userName = inflater1.findViewById(R.id.username);
        userEmail = inflater1.findViewById(R.id.userEmail);

        profileImage= inflater1.findViewById(R.id.profile_image);

        currentUser= ParseUser.getCurrentUser();

        //below code will set the profile view

        String loc= "<b>"+"Favourite study Location: "+"</b>" +currentUser.getString("studyLocation") ;
        String maj="<b>"+"Major: "+"</b>" +currentUser.getString("major");
        String des="<b>"+"Description: "+"</b>" +currentUser.getString("personalDescription");
        String email="<b>"+"Email: "+"</b>" + currentUser.getEmail();

        location.setText(Html.fromHtml(loc));
        major.setText(Html.fromHtml(maj));
        description.setText(Html.fromHtml(des));
        userName.setText(currentUser.getString("name"));
        userEmail.setText(Html.fromHtml(email));

        editButton= inflater1.findViewById(R.id.editProfileButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "edit button pressed", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), ProfileEditActivity.class);
                startActivity(intent);

            }
        });

        ParseUser currentUser= ParseUser.getCurrentUser();

        ParseFile file= (ParseFile) currentUser.get("Profile_Image");

        file.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] data, ParseException e) {
                if(e==null && data!=null)
                {
                    Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);

                    profileImage.setImageBitmap(bitmap);
                }
            }
        });










        /*if(profileImage!=null) {


            Glide.with(this).load(personPhoto).into((ImageView) profileImage);
        }
        else
        {
            //Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //startActivity(intent);

        }*/

        return inflater1;
    }




}
