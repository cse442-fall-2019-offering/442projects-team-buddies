package demo.app.a442projects_team_buddies;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PracticeTestContent  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_test_content);

        TextView txtInfo = findViewById(R.id.test_content);
        if (getIntent() != null) {
            String info = getIntent().getStringExtra("info");
            txtInfo.setText(info);
        }
    }
}