package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CourseViewActivity extends AppCompatActivity {
    private DrawerLayout drawLayout;
    private ActionBarDrawerToggle toggle;
    private FloatingActionButton btn_joinClass;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        toggle = new ActionBarDrawerToggle(this,drawLayout,R.string.open,R.string.close);
        drawLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

// THIS SHIT BELOW IS TO THE WRONG DESTINATION. MUST BE DIRECTED TOWARDS SEARCHACTIVITY BUT IT GIVES
//ME A HARD TIME WHEN EVER I DIRECT IT THERE

        intent = new Intent(this, SearchActivity.class);
        btn_joinClass = findViewById(R.id.fab);
        btn_joinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });



    }

    public void initWidgets(){
        drawLayout = findViewById(R.id.drawerLayout);
    }

    public void myProfile(MenuItem menuItem) {
        setContentView(R.layout.profile_page);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

