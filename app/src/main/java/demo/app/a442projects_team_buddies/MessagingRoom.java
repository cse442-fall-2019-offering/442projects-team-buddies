package demo.app.a442projects_team_buddies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MessagingRoom extends AppCompatActivity {
    static final int MAX_CHAT_MESSAGES_TO_SHOW = 50;


    EditText messageInput;
    Button sendButton;
    RecyclerView chatrv;
    ArrayList<Chatting> messages;
    MessageRoomAdapter mAdapter;
    boolean mFirstLoad;
    String sUserId;
    String sFriendsId;



    void setupMessagePosting() {
        // Find the text field and button
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(c);
        messageInput = (EditText) findViewById(R.id.messageBeingSent);
        sendButton = (Button) findViewById(R.id.sendMessageButton);
        chatrv=(RecyclerView) findViewById(R.id.messageView);
        messages=new ArrayList<>();
        mFirstLoad=true;

        final String userId = ParseUser.getCurrentUser().getObjectId();
        mAdapter = new MessageRoomAdapter(MessagingRoom.this, userId, messages);
        chatrv.setAdapter(mAdapter);



        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MessagingRoom.this);
       // linearLayoutManager.setReverseLayout(true);
        chatrv.setLayoutManager(linearLayoutManager);


        // When send button is clicked, create message object on Parse
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = messageInput.getText().toString();
                if(data.length()>0) {


                    String receiver = getIntent().getStringExtra("friendsObjectId");
                    sUserId = ParseUser.getCurrentUser().getUsername();
                    sFriendsId = receiver;
                    Chatting message = new Chatting();
                    message.setBody(data);
                    message.setUserId(ParseUser.getCurrentUser().getUsername());
                    message.setReceiverId(receiver);

                message.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null) {
                            Toast.makeText(MessagingRoom.this, "Successfully created message on Parse",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(MessagingRoom.class.getSimpleName(), "Failed to save message", e);
                        }
                    }
                });
                messageInput.setText(null);
            }
        }});
    }

    void startWithCurrentUser() {
        setupMessagePosting();
    }


    void refreshMessages() {
        // Sent Messages Query
        ParseQuery<Chatting> sentMessagesQuery = ParseQuery.getQuery(Chatting.class);
        sentMessagesQuery.whereEqualTo("userId", sUserId);
        sentMessagesQuery.whereEqualTo("receiverId", sFriendsId);

        // Receiver Messages Query
        ParseQuery<Chatting> receiveMessagesQuery = ParseQuery.getQuery(Chatting.class);
        receiveMessagesQuery.whereEqualTo("userId", sFriendsId);
        receiveMessagesQuery.whereEqualTo("receiverId", sUserId); //receiver is me (current user)

        // Combine the queries
        List<ParseQuery<Chatting>> queries = new ArrayList<>();
        queries.add(sentMessagesQuery);
        queries.add(receiveMessagesQuery);

        // Get the messages
        ParseQuery<Chatting> mainQuery = ParseQuery.or(queries);
        // Configure limit and sort order
        mainQuery.setLimit(MAX_CHAT_MESSAGES_TO_SHOW);
        mainQuery.orderByAscending("createdAt");

        mainQuery.findInBackground(new FindCallback<Chatting>() {
            public void done(List<Chatting> message, ParseException e) {
                if (e == null) {
                    messages.clear();
                    messages.addAll(message);
                    mAdapter.notifyDataSetChanged(); // update adapter
                    // Scroll to the bottom of the list on initial load
                    if (mFirstLoad) {
                        chatrv.scrollToPosition(0);
                        mFirstLoad = false;
                    }
                } else {
                    Log.e("message", "Error Loading Messages" + e);
                }
            }
        });
    }

    static final int POLL_INTERVAL = 1000; // milliseconds
    Handler myHandler = new Handler();  // android.os.Handler
    Runnable mRefreshMessagesRunnable = new Runnable() {
        @Override
        public void run() {
            refreshMessages();
            myHandler.postDelayed(this, POLL_INTERVAL);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_room);

            startWithCurrentUser();
        myHandler.postDelayed(mRefreshMessagesRunnable, POLL_INTERVAL);

    }

}


