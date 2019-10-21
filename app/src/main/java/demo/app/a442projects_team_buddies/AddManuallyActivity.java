package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddManuallyActivity extends AppCompatActivity {
    private Button btn_addClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manually);
        init();
        btn_addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addClassManually = new Intent(AddManuallyActivity.this, CourseViewActivity.class);

                startActivity(addClassManually);
            }
        });

    }

    public void init(){
        btn_addClass = findViewById(R.id.btn_joinClass);
    }
}
