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
    }

    public void init(){
        btn_addClass = findViewById(R.id.btn_joinClass);
    }

    public void addClass(){
        // Step1 get the cName, cNum, and pName, and write these data into user's record
        // Step2 go back to the course view activity and update the list

        // Do step 1


        // Step 2
        btn_addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addClassManually = new Intent(AddManuallyActivity.this, CourseViewActivity.class);

                startActivity(addClassManually);
            }
        });

    }
}
