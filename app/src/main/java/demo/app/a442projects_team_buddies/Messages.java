package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        String userNames[]= new String[10];
        for(int i =0 ; i< 10 ; i++){
            images[i]= R.drawable.photo_circle;
            userNames[i] = "user"+(i+1);
            }
      //replace with for each user in match database

        ListView lv =(ListView)findViewById(R.id.messagesList);

        MyListAdapter mlAdapter = new MyListAdapter(this,userNames,images);
        lv.setAdapter(mlAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick (AdapterView < ? > adapterView, View view,int i, long l){

                 for (int x=0; x<10 ; x++) {
                    if(x==i){
                    Intent intent = new Intent(Messages.this, MessagingRoom.class);
                    startActivity(intent);
                    }
                }

            }

        });

    }


}
