package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.parse.ParseUser;

// user_name

public class MainActivity extends AppCompatActivity {
    private ParseUser cur_user;
    private TextView cur_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        cur_user = ParseUser.getCurrentUser();
//        cur_username = findViewById(R.id.user_name);
//        String set_userName = cur_user.getUsername().toString() ;
//        cur_username.setText("lllsalsl");

        setContentView(R.layout.activity_main);
    }
}

