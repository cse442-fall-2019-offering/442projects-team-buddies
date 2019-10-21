package demo.app.a442projects_team_buddies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class ProfileEditActivity extends AppCompatActivity {

    private ParseUser currentUser;

    private Button saveButton;
    EditText editLocation, editMajor, editDescription, editUsersName, editEmail;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUserTheme(ParseUser.getCurrentUser().getInt("theme"));
        setContentView(R.layout.profile_edit_page);
        currentUser = ParseUser.getCurrentUser();
        saveButton= findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });


    }

    public void saveChanges() {

        //Text boxes in the profile_edit_page

        editDescription = findViewById(R.id.editTextDescription);
        editLocation = findViewById(R.id.editTextLocation);
        editMajor = findViewById(R.id.editTextMajor);
        editUsersName = findViewById(R.id.editUsersName);
        editEmail = findViewById(R.id.editEmail);

        //Getting the strings of the editTexts
        String newDescription, newLocation, newMajor, newUserName, newEmail;
        newDescription = editDescription.getText().toString();
        newLocation = editLocation.getText().toString();
        newMajor = editMajor.getText().toString();
        newUserName = editUsersName.getText().toString();
        newEmail = editEmail.getText().toString();

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
        if(!newUserName.isEmpty()) {
            currentUser.put("name", newUserName);
        }
        if(!newEmail.isEmpty()) {
            currentUser.put("email", newEmail);
        }

        currentUser.saveInBackground();

        //this code will update the profile
        Intent intent = new Intent(this,CourseViewActivity.class);
        startActivity(intent);
    }

    public void setUserTheme(int theme) {
        if(theme == 0) {
            setTheme(R.style.defaultAppTheme);
        }
        if(theme == 1) {
            setTheme(R.style.redAppTheme);
        }
        if(theme == 2) {
            setTheme(R.style.greenAppTheme);
        }
        if(theme == 3) {
            setTheme(R.style.yellowAppTheme);
        }
    }


}
