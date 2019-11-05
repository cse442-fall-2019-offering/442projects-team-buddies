package demo.app.a442projects_team_buddies;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class UpdateHeaderSection extends AppCompatActivity {
    TextView username;
    TextView email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sm_header_section);
        update_Header();
        finish();
    }

    public void update_Header()
    {
        username= findViewById(R.id.user_name);
        email= findViewById(R.id.user_email);

        username.setText(ParseUser.getCurrentUser().getUsername());
        email.setText(ParseUser.getCurrentUser().getEmail());
        
    }
}
