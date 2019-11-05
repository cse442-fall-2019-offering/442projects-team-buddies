package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Messages extends AppCompatActivity {
    Toolbar toolbar;
    String friendList[];
    Integer images[];



    public void addFriends(int size, List<ParseObject> friends){

        friendList = new String[size];
        images= new Integer[size];
        int i=0;
        for(ParseObject person: friends){
            String userFriend= person.toString();
            friendList[i]=userFriend;
            images[i]= R.drawable.photo_circle;  // needs to change for each user picture

            i++;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);


        ParseQuery<ParseObject> userFriends= ParseQuery.getQuery("User_Friends");
        userFriends.whereEqualTo("user_name", ParseUser.getCurrentUser().getUsername());
        userFriends.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> friendList, ParseException e) {
                if (e == null) {
                        addFriends(friendList.size(),friendList);
                }
            }
        });









        toolbar = findViewById(R.id.MessageBar);
        //setSupportActionBar(toolbar);
       // getSupportActionBar().setTitle("Messages");
        toolbar.setLogo(R.drawable.ic_arrow_back_black_24dp);  //go back button



        ListView lv =(ListView)findViewById(R.id.messagesList);

        MyListAdapter mlAdapter = new MyListAdapter(this,friendList,images);
        lv.setAdapter(mlAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick (AdapterView < ? > adapterView, View view,int i, long l){

                 for (int x=0; x<friendList.length ; x++) {
                    if(x==i){
                    Intent intent = new Intent(Messages.this, MessagingRoom.class);
                    startActivity(intent);
                    }
                }

            }

        });

    }


}
