package demo.app.a442projects_team_buddies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class FriendListActivity extends AppCompatActivity {
    public ListView userListView;

    public ArrayList<String> arrayList;
    public ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_listview);
        userListView= findViewById(R.id.userListView);
        arrayList= new ArrayList<>();
        arrayList.clear();
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent behaviorIntent= getIntent();
                String str= behaviorIntent.getStringExtra("behaviour");
                if(str.equals("chat")) {
                    Intent intent = new Intent(FriendListActivity.this, ChatViewActivity.class);

                    intent.putExtra("username", arrayList.get(position));
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(FriendListActivity.this, PracticeTestView.class);

                    intent.putExtra("username", arrayList.get(position));
                    startActivity(intent);

                }
            }
        });


        arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        userListView.setAdapter(arrayAdapter);


        ParseQuery<ParseObject> user= ParseQuery.getQuery("User_Friends");
        user.whereEqualTo("user_name", ParseUser.getCurrentUser().getUsername());
        user.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null &&objects.size()>0)
                {
                    for (ParseObject object:objects)
                    {
                        arrayList.add(object.getString("user_friends"));
                    }

                    arrayAdapter.notifyDataSetChanged();
                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}


