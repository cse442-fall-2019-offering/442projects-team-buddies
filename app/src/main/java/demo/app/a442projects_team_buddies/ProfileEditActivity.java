package demo.app.a442projects_team_buddies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileEditActivity extends AppCompatActivity {

    private ParseUser currentUser;
    CircleImageView profileImage;
    private Button saveButton;
    EditText editLocation, editMajor, editDescription, editUsersName, editEmail;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit_page);
        currentUser = ParseUser.getCurrentUser();
        saveButton= findViewById(R.id.saveButton);

        profileImage= findViewById(R.id.profile_image);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
                finish();
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
        {
            Uri image= data.getData();

            try {
                Bitmap bitmap =MediaStore.Images.Media.getBitmap(this.getContentResolver(),image);

                ByteArrayOutputStream stream= new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);

                byte[] byteArray= stream.toByteArray();
                ParseFile file= new ParseFile("profileImage.png",byteArray);

                ParseUser currentUser= ParseUser.getCurrentUser();
                currentUser.put("Profile_Image",file);

                currentUser.saveInBackground();

            } catch (IOException e) {
                e.printStackTrace();
            }

            profileImage.setImageURI(image);
        }
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

}
