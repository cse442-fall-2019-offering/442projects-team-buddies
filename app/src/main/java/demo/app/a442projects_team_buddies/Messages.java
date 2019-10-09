package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Messages extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        toolbar = findViewById(R.id.MessageBar);
        //setSupportActionBar(toolbar);
       // getSupportActionBar().setTitle("Messages");
        toolbar.setLogo(R.drawable.ic_arrow_back_black_24dp);  //go back button
        Integer images[] = new Integer[10];
        for(int i =0 ; i< 10 ; i++){
            images[i]= R.drawable.photo_circle;
            }
        String userNames[]= {"user1","user2","user3","user4","user5","user6","user7","user8","user9"}; //replace with for each user in match database

        ListView lv =(ListView)findViewById(R.id.messagesList);

        ListAdapter mlAddapter = new ArrayAdapter<>(this,android.R.layout.

    }

}
