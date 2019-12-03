package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {
    String activeUser="";
    public ArrayList<String> chatList= new ArrayList<>();

    public ArrayAdapter arrayAdapter;

    FloatingActionButton sendFAB;

    public void sendChat(View view)
    {
        final EditText chatText= findViewById(R.id.chatText);


        final ParseObject message= new ParseObject("Message");

        message.put("sender", ParseUser.getCurrentUser().getUsername());
        message.put("recipient",activeUser);
        message.put("message",chatText.getText().toString());

        message.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Toast.makeText(ChatRoomActivity.this,"message sent",Toast.LENGTH_LONG).show();
                chatList.clear();
                chatList.add(chatText.getText().toString());
                arrayAdapter.notifyDataSetChanged();
                chatText.setText("");
                updateMessageList();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        Intent intent=getIntent();
        setUserTheme(ParseUser.getCurrentUser().getInt("theme"));

        activeUser= intent.getStringExtra("username");
        setTitle(activeUser);
        final ListView chatListView= findViewById(R.id.chatList);
        arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,chatList);

        chatListView.setAdapter(arrayAdapter);

        updateMessageList();




    }
    public void setUserTheme(int theme) {
        if(theme == 0) {
            setTheme(R.style.defaultAppTheme);
        }
        if(theme == 1) {
            setTheme(R.style.redAppTheme);
        }
        if(theme == 2) {
            setTheme(R.style.greenAppTheme);
        }
        if(theme == 3) {
            setTheme(R.style.yellowAppTheme);
        }
    }

    public void updateMessageList()
    {

        ParseQuery<ParseObject> query1= new ParseQuery<ParseObject>("Message");

        query1.whereEqualTo("sender",ParseUser.getCurrentUser().getUsername());
        query1.whereEqualTo("recipient",activeUser);

        ParseQuery<ParseObject> query2= new ParseQuery<ParseObject>("Message");

        query2.whereEqualTo("recipient",ParseUser.getCurrentUser().getUsername());
        query2.whereEqualTo("sender",activeUser);
        List<ParseQuery<ParseObject>> queries= new ArrayList<>();

        queries.add(query1);
        queries.add(query2);

        ParseQuery<ParseObject> query= ParseQuery.or(queries);

        query.orderByAscending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null)
                {
                    if (objects.size()>0)
                    {
                        chatList.clear();
                        for (ParseObject message:objects)
                        {
                            String messageContent= message.getString("message");
                            if (!message.getString("sender").equals(ParseUser.getCurrentUser().getUsername())) {
                                messageContent= "> "+ messageContent;
                            }
                            chatList.add(messageContent);
                        }
                        arrayAdapter.notifyDataSetChanged();
                        refresh(1000);
                    }
                }
            }
        });



    }

    private void refresh(int i) {
        final Handler handler=new Handler();

        final Runnable runnable =new Runnable() {
            @Override
            public void run() {
                updateMessageList();
            }
        };

        handler.postDelayed(runnable,i);
    }
}
