package demo.app.a442projects_team_buddies;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class ChatViewActivity extends AppCompatActivity {
    String activeUser="";
    boolean isActive;


    public ArrayAdapter arrayAdapter;


    private RecyclerView rView ;
    private ChatViewAdapter rViewAdapter;

    private RecyclerView.LayoutManager rViewLayoutManager;

    ArrayList<ChatViewItem> messages= new ArrayList<>();
    EditText chatText;


    FloatingActionButton sendFAB;

    public void sendMessage(View view)
    {

        ParseQuery<ParseObject> checkFriendQuery= ParseQuery.getQuery("User_Friends");

        checkFriendQuery.whereEqualTo("user_name",activeUser);
        checkFriendQuery.whereEqualTo("user_friends",ParseUser.getCurrentUser().getUsername());

        checkFriendQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null)
                {
                    if(objects.size()==0)
                    {
                        ParseObject friendList= new ParseObject("User_Friends");

                        friendList.put("user_name",activeUser);
                        friendList.put("user_friends",ParseUser.getCurrentUser().getUsername());
                        friendList.saveInBackground();
                    }
                }
            }
        });



        final ParseObject message= new ParseObject("Message");

        message.put("sender", ParseUser.getCurrentUser().getUsername());
        message.put("recipient",activeUser);
        message.put("message",chatText.getText().toString());



        message.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Toast.makeText(ChatViewActivity.this,"message sent",Toast.LENGTH_LONG).show();
                //messages.clear();
                //messages.add(new ChatViewItem(chatText.getText().toString()));
                //arrayAdapter.notifyDataSetChanged();
                chatText.setText("");
                updateMessageList();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatview);
        Intent intent=getIntent();
        chatText= findViewById(R.id.messageEditText);
        isActive= true;

        activeUser= intent.getStringExtra("username");
        setTitle(activeUser);
        //messages.add(new ChatViewItem("first message"));

        rView= findViewById(R.id.recyclerView);

        rView.setHasFixedSize(true);  // if the view changes in size then comment out this line
        rViewLayoutManager = new LinearLayoutManager(getBaseContext());



        rViewAdapter = new ChatViewAdapter(messages);
        rView.setLayoutManager(rViewLayoutManager);
        rView.setAdapter(rViewAdapter);


        updateMessageList();




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
                        messages.clear();
                        for (ParseObject message:objects)
                        {
                            String messageContent= message.getString("message");
                            if (!message.getString("sender").equals(ParseUser.getCurrentUser().getUsername())) {
                                //messageContent= "> "+ messageContent;
                                messages.add(new ChatViewItem(messageContent,activeUser));
                            }
                            else
                            {
                                messages.add(new ChatViewItem(messageContent,"right"));
                            }
                        }
                        rViewAdapter.notifyDataSetChanged();
                        if(isActive)
                        {
                            refresh(1000);
                        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isActive= false;
    }
}
