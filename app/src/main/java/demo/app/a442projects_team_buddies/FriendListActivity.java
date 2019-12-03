package demo.app.a442projects_team_buddies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
        setTitle("User Friends");
        setTheme(R.style.yellowAppTheme);
        setUserTheme((int) ParseUser.getCurrentUser().getNumber("theme"));
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



        userListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(FriendListActivity.this);
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Confirm to delete the "+ arrayList.get(position)+" from your friend list");
                alertDialog.setNegativeButton( "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                saveChanges(position);
                                arrayList.remove(position);
                                arrayAdapter.notifyDataSetChanged();

                                dialog.dismiss();
                            }
                        });
                alertDialog.setPositiveButton( "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });
                AlertDialog alert= alertDialog.create();
                alert.show();


                return true;
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

    public void saveChanges(int position)
    {



        ParseQuery<ParseObject> query1= new ParseQuery<ParseObject>("User_Friends");

        query1.whereEqualTo("user_name",ParseUser.getCurrentUser().getUsername());
        query1.whereEqualTo("user_friends",arrayList.get(position));

        ParseQuery<ParseObject> query2= new ParseQuery<ParseObject>("User_Friends");

        query2.whereEqualTo("user_friends",ParseUser.getCurrentUser().getUsername());
        query2.whereEqualTo("user_name",arrayList.get(position));
        List<ParseQuery<ParseObject>> queries= new ArrayList<>();

        queries.add(query1);
        queries.add(query2);

        ParseQuery<ParseObject> query_del= ParseQuery.or(queries);

        query_del.orderByAscending("createdAt");
        query_del.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null)
                {
                    if (objects.size()>0)
                    {

                        for (ParseObject message:objects)
                        {
                            message.deleteInBackground();
                        }


                    }
                }
            }
        });


       delete_Messages(position);

        delete_PracticeQuiz(position);

    }

    public void delete_Messages(int position)
    {

        ParseQuery<ParseObject> query1= new ParseQuery<ParseObject>("Message");

        query1.whereEqualTo("sender",ParseUser.getCurrentUser().getUsername());
        query1.whereEqualTo("recipient",arrayList.get(position));

        ParseQuery<ParseObject> query2= new ParseQuery<ParseObject>("Message");

        query2.whereEqualTo("recipient",ParseUser.getCurrentUser().getUsername());
        query2.whereEqualTo("sender",arrayList.get(position));
        List<ParseQuery<ParseObject>> queries= new ArrayList<>();

        queries.add(query1);
        queries.add(query2);

        ParseQuery<ParseObject> query_del= ParseQuery.or(queries);

        query_del.orderByAscending("createdAt");
        query_del.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null)
                {
                    if (objects.size()>0)
                    {

                        for (ParseObject message:objects)
                        {
                            message.deleteInBackground();
                        }


                    }
                }
            }
        });
    }

    public void delete_PracticeQuiz(int position)
    {
        ParseQuery<ParseObject> query1= new ParseQuery<ParseObject>("Practice_Quiz");

        query1.whereEqualTo("sender",ParseUser.getCurrentUser().getUsername());
        query1.whereEqualTo("recipient",arrayList.get(position));

        ParseQuery<ParseObject> query2= new ParseQuery<ParseObject>("Practice_Quiz");

        query2.whereEqualTo("recipient",ParseUser.getCurrentUser().getUsername());
        query2.whereEqualTo("sender",arrayList.get(position));
        List<ParseQuery<ParseObject>> queries= new ArrayList<>();

        queries.add(query1);
        queries.add(query2);

        ParseQuery<ParseObject> query_del= ParseQuery.or(queries);

        query_del.orderByAscending("createdAt");
        query_del.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null)
                {
                    if (objects.size()>0)
                    {

                        for (ParseObject quiz:objects)
                        {
                           quiz.deleteInBackground();
                        }


                    }
                }
            }
        });
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}


