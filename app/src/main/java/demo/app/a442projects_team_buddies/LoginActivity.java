package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Intent intent;
    private EditText user;
    private EditText password;
    private Button signUpButton1;
    private Button signUpButton2;
    private EditText email;
    private ImageButton googleAuthButton;

    int flag=0; //if 1 signup using google else signup normally

    GoogleSignInClient mGoogleSignInClient;

    int RC_SIGN_IN=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        intent = new Intent(this, CourseViewActivity.class);
        loginButton = findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(intent);

                login(loginButton);

                //setContentView(R.layout.profile_page);
            }
        });

        signUpButton1 = findViewById(R.id.signUp_btn);

        signUpButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.signup);
                signUpButton2= findViewById(R.id.button);
                signUpButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signUp(signUpButton2);
                    }
                });

            }
        });

        //Google Authentication start from here

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googleAuthButton = (ImageButton) findViewById(R.id.cart);
        googleAuthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "Hello I am google", Toast.LENGTH_LONG).show();
                switch (v.getId()) {
                    case R.id.cart:
                        signIn();
                        break;
                }

            }
        });

    }

    private void signIn() {

        //Log.i("Status","Inside Signin method");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Status","Inside onActivityResult method");

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {

        Log.i("Status","Inside last method");
        try {
            //Log.i("Status","above");
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            //Log.i("Status","below");
            Toast.makeText(getBaseContext(), "Hello I am google", Toast.LENGTH_LONG).show();

            // Signed in successfully, show authenticated UI.
            //----------------------------------------------------------


            signUp();

            //---------------------------------------------------------
            Intent intent = new Intent(this,CourseViewActivity.class);
            startActivity(intent);
            //setContentView(R.layout.secondactivity);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());

        }
    }







    protected void login(Button loginButton)
    {

        user = (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);

        String u= String.valueOf(user.getText());
        String p = String.valueOf(password.getText());
        //Toast.makeText(getBaseContext(), p, Toast.LENGTH_LONG).show();

        ParseUser.logInInBackground(u, p, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user!=null)
                {
                    Toast.makeText(getBaseContext(), "Successful", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getBaseContext(), "failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    protected void signUp(Button signUpButton)
    {
        email= (EditText)findViewById(R.id.user_email) ;
        user = (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);

        final ParseUser newUser= new ParseUser();

        newUser.setEmail(String.valueOf(email.getText()));
        newUser.setUsername(String.valueOf(user.getText()));
        newUser.setPassword(String.valueOf(password.getText()));

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null)
                        {
                            Toast.makeText(getBaseContext(), "Successful", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(), "failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


    }

    protected void signUp()
    {

        final ParseUser newUser= new ParseUser();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();

            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();



            newUser.setEmail(personEmail);
            newUser.setUsername(personName);
            //newUser.setPassword("1234556");
            //newUser.put("Profile photo",String.valueOf(personPhoto));



           // Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);
        }

        newUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null)
                {
                    Toast.makeText(getBaseContext(), "Successful", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getBaseContext(), "failed", Toast.LENGTH_LONG).show();
                }
            }
        });


    }



}
