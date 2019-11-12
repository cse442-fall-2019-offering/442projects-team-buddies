package demo.app.a442projects_team_buddies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class MessagingRoom extends AppCompatActivity {
    static final int MAX_CHAT_MESSAGES_TO_SHOW = 50;
    EditText messageInput;
    Button sendButton;
    RecyclerView chatrv;
    ArrayList<Chatting> messages;
    MessageRoomAdapter mAdapter;
    // Keep track of initial load to scroll to the bottom of the ListView
    boolean mFirstLoad;


    // Get the userId from the cached currentUser object
    void startWithCurrentUser() {
        setupMessagePosting();
    }

    // Setup button event handler which posts the entered message to Parse
    void setupMessagePosting() {
        // Find the text field and button
        messageInput = (EditText) findViewById(R.id.messageBeingSent);
        sendButton = (Button) findViewById(R.id.sendMessageButton);
        chatrv = (RecyclerView) findViewById(R.id.messageView);
        messages = new ArrayList<>();
        mFirstLoad = true;
        final String userId = ParseUser.getCurrentUser().getObjectId();
        mAdapter = new MessageRoomAdapter(MessagingRoom.this, userId, messages);
        chatrv.setAdapter(mAdapter);


        // associate the LayoutManager with the RecylcerView
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MessagingRoom.this);
        chatrv.setLayoutManager(linearLayoutManager);
        // When send button is clicked, create message object on Parse
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = messageInput.getText().toString();

                Chatting message = new Chatting();
                message.setBody(data);
                message.setUserId(ParseUser.getCurrentUser().getObjectId());
                message.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(MessagingRoom.this, "Successfully created message on Parse",
                                    Toast.LENGTH_SHORT).show();

                            refreshMessages(); //now we need to refresh our messages

                        } else {
                            Log.e( "error", "Failed to save message", e);
                        }
                    }
                });
                messageInput.setText(null);
            }
        });
    }

    // Query messages from Parse so we can load them into the chat adapter
    void refreshMessages() {
        // Construct query to execute
        ParseQuery<Chatting> query = ParseQuery.getQuery(Chatting.class);
        // Configure limit and sort order
        query.setLimit(MAX_CHAT_MESSAGES_TO_SHOW);

        // get the latest 50 messages, order will show up newest to oldest of this group
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<Chatting>() {
            public void done(List<Chatting> messages, ParseException e) {
                if (e == null) {
                    messages.clear();
                    messages.addAll(messages);
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









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_room);
        startWithCurrentUser();

    }
}
