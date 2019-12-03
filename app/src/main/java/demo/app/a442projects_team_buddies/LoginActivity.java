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
import android.widget.ImageView;
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
import com.parse.RequestPasswordResetCallback;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;


    private EditText user;
    private EditText password;
    private Button signUpButton;

    private TextView forgetPassword;
    private EditText email;
    private ImageButton googleAuthButton;

    public int selectedMenu=0 ;

    //int flag=0; //if 1 signup using google else signup normally

    GoogleSignInClient mGoogleSignInClient;

    int RC_SIGN_IN=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);



        loginButton = findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login(loginButton);

            }
        });

        signUpButton = findViewById(R.id.signUp_btn);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent intent= new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);

            }
        });

        forgetPassword= findViewById(R.id.resetPassword);

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "successfully sent", Toast.LENGTH_LONG).show();
                ParseUser.requestPasswordResetInBackground("singhvikram855@live.com", new RequestPasswordResetCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // An email was successfully sent with reset instructions.
                            Toast.makeText(getBaseContext(), "successfully sent", Toast.LENGTH_LONG).show();
                        } else {
                            // Something went wrong. Look at the ParseException to see what's up.
                            Toast.makeText(getBaseContext(), "not valid", Toast.LENGTH_LONG).show();
                            Log.i("Result:","failed"+e.toString());
                        }
                    }
                });
            }
        });

        ParseUser currentUser = ParseUser.getCurrentUser();




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

    @Override
    protected void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null)
        {
            //authenticatedUI();
        }
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

            // Signed in successfully, show authenticated UI.//+++++++++++++++++++++++++++++++++++++++++++++
            //----------------------------------------------------------


            signUp();

            //---------------------------------------------------------
           authenticatedUI();
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

                    // Signed in successfully, show authenticated UI.+++++++++++++++++++++++++++++++++++++

                    authenticatedUI();


                    ((EditText) findViewById(R.id.username)).setText("");
                    ((EditText) findViewById(R.id.password)).setText("");

                }
                else
                {
                    Toast.makeText(getBaseContext(), "Not a valid user" , Toast.LENGTH_LONG).show();
                }
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




           //ProfileFragment.personPhoto= String.valueOf(personPhoto);
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


    public void authenticatedUI()
    {
        Intent intent = new Intent(this,CourseViewActivity.class);
        startActivity(intent);
    }

    public void changeDefaultFragment(){
        selectedMenu=1;
    }

    public int getSelectedItem()
    {
        return  selectedMenu;
    }



}
