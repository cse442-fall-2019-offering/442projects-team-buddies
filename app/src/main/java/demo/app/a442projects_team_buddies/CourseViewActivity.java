package demo.app.a442projects_team_buddies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.parse.ParseUser;

public class CourseViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawLayout;
    private ActionBarDrawerToggle toggle;





    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUserTheme(ParseUser.getCurrentUser().getInt("theme"));
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);




        drawLayout = findViewById(R.id.drawerLayout);



        toggle = new ActionBarDrawerToggle(this,drawLayout,R.string.open,R.string.close);
        drawLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView= findViewById(R.id.nav_view);

        LoginActivity loginActivity= new  LoginActivity();

        navigationView.setNavigationItemSelectedListener(this);

        //Intent intent= new Intent(this,UpdateHeaderSection.class);
        //startActivity(intent);


        if(savedInstanceState==null  )
        {
            setTitle("Profile Page");
            //loginActivity.changeDefaultFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CourseFragment()).commit();

        }





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



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.m_profile )
        {
            setTitle("Profile Page");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
        }
        else if(menuItem.getItemId()==R.id.m_course  )
        {
            setTitle("Course Enrolled");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CourseFragment(),"courseFragment").commit();

        }
        else if(menuItem.getItemId()==R.id.setting )
        {
            Intent intent = new Intent(this, settingsActivity.class);
            startActivity(intent);
        }
        else if(menuItem.getItemId()==R.id.m_chatrooms )
        {
            Intent intent = new Intent(this, FriendListActivity.class);
            intent.putExtra("behaviour","chat");
            startActivity(intent);
        }
        else if(menuItem.getItemId()==R.id.activities)
        {
            Intent intent = new Intent(this, FriendListActivity.class);
            intent.putExtra("behaviour","quiz");
            startActivity(intent);
        }
        else if(menuItem.getItemId()==R.id.logout )
        {


            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            if (account!=null)
            {
                signOut();
            }
            else {
                ParseUser.logOut();

                //Intent intent = new Intent(CourseViewActivity.this,LoginActivity.class);
                //startActivity(intent);
                finish();
            }
        }
        return true;
    }





    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...

                        finish();

                    }
                });
    }



}
