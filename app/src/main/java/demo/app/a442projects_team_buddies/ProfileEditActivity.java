package demo.app.a442projects_team_buddies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class ProfileEditActivity extends AppCompatActivity {

    private ParseUser currentUser = ParseUser.getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit_page);


    }

    public void saveChanges(View view) {

        //Text boxes in the profile_edit_page
        EditText editLocation, editMajor, editDescription;
        editDescription = findViewById(R.id.editTextDescription);
        editLocation = findViewById(R.id.editTextLocation);
        editMajor = findViewById(R.id.editTextMajor);

        //Getting the strings of the editTexts
        String newDescription, newLocation, newMajor;
        newDescription = editDescription.getText().toString();
        newLocation = editLocation.getText().toString();
        newMajor = editMajor.getText().toString();

        //Setting the new information as long as the user didn't leave the editTexts empty
        if(!newDescription.isEmpty()) {
            currentUser.put("personalDescription", newDescription);
        }
        if(!newLocation.isEmpty()) {
            currentUser.put("studyLocation", newLocation);
        }
        if(!newMajor.isEmpty()) {
            currentUser.put("major", newMajor);
        }

        Intent intent = new Intent(this, ProfileActivity.class);

        startActivity(intent);
    }

}
