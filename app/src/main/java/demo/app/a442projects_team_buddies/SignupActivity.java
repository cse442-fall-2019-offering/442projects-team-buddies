package demo.app.a442projects_team_buddies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    private EditText email;
    private EditText user;
    private EditText password;
    private Button signUpButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);

        signUpButton = findViewById(R.id.button);
        email = (EditText) findViewById(R.id.user_email);
        user = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });


    }

    protected void signUp() {

        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        final ParseUser newUser = new ParseUser();

        newUser.setEmail(String.valueOf(email.getText()));
        newUser.setUsername(String.valueOf(user.getText()));
        newUser.setPassword(String.valueOf(password.getText()));


        //newUser.isAuthenticated();


        if (email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "enter email address", Toast.LENGTH_SHORT).show();
        } else if (email.getText().toString().trim().matches(emailPattern)) {
            //Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
            newUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(getBaseContext(), "Successful", Toast.LENGTH_LONG).show();
                        // Signed in successfully, show authenticated UI.+++++++++++++++++++++++++++++++++++++

                        Intent intent = new Intent(SignupActivity.this,CourseViewActivity.class);
                        startActivity(intent);
                        finish();


                        ((EditText) findViewById(R.id.username)).setText("");
                        ((EditText) findViewById(R.id.password)).setText("");
                        email.setText("");
                    } else {
                        Toast.makeText(getBaseContext(), "User already exist", Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
        }


    }
}
