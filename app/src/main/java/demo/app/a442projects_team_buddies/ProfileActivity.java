package demo.app.a442projects_team_buddies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
    }

    public void editMyProfile(View view) {
        Intent intent = new Intent(this, ProfileEditActivity.class);

        startActivity(intent);
    }

}
