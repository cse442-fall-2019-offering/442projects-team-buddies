package demo.app.a442projects_team_buddies;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {

    ImageView profileImage;
    static String personPhoto;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View inflater1=inflater.inflate(R.layout.profile_page,container,false);

        profileImage= inflater1.findViewById(R.id.pro);
        if(profileImage!=null) {


            Glide.with(this).load(personPhoto).into((ImageView) profileImage);
        }

        return inflater1;
    }


}
