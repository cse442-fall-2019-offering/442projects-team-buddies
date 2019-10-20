package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


public class CourseViewActivity extends AppCompatActivity {
    private DrawerLayout drawLayout;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        toggle = new ActionBarDrawerToggle(this,drawLayout,R.string.open,R.string.close);
        drawLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void initWidgets(){
        drawLayout = findViewById(R.id.drawerLayout);
    }

    public void myProfile(MenuItem menuItem) {
        Intent intent = new Intent(this, ProfileActivity.class);

        startActivity(intent);
    }

    public void mySettings(MenuItem menuItem) {
        Intent intent = new Intent(this, settingsActivity.class);

        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
