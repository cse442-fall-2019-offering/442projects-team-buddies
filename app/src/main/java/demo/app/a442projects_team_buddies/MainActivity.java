package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btn_joinClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btn_joinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchClass();
            }
        });
    }
    public void init(){
        btn_joinClass = findViewById(R.id.fab);
    }
    public void searchClass(){
        Intent searchClass = new Intent(MainActivity.this, SearchActivity.class);

        startActivity(searchClass);
    }
}