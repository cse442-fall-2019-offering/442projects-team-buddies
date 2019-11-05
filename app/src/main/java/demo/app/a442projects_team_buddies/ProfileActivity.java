package demo.app.a442projects_team_buddies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class ProfileActivity extends AppCompatActivity {

    private ParseUser currentUser = ParseUser.getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView location, major, description;
        location = findViewById(R.id.textViewProfileLocation);
        major = findViewById(R.id.textViewProfileMajor);
        description = findViewById(R.id.textViewProfileDescription);

        location.setText(currentUser.getString("studyLocation"));
        major.setText(currentUser.getString("major"));
        description.setText(currentUser.getString("personalDescription"));

        setContentView(R.layout.profile_page);

    }

    public void editMyProfile(View view) {
        Intent intent = new Intent(this, ProfileEditActivity.class);

        startActivity(intent);
    }

}
