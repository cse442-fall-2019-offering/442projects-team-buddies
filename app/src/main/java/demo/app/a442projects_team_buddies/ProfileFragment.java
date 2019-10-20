package demo.app.a442projects_team_buddies;

import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.parse.ParseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {


    CircleImageView profileImage;
    //static String personPhoto;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View inflater1=inflater.inflate(R.layout.profile_page,container,false);

        profileImage= inflater1.findViewById(R.id.profile_image);


       profileImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               startActivityForResult(intent,1);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
        {
            Uri image= data.getData();



            profileImage.setImageURI(image);
        }
    }


}
