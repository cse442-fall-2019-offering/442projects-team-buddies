package demo.app.a442projects_team_buddies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class settingsActivity extends AppCompatActivity {


    ParseUser currentUser = ParseUser.getCurrentUser();

    Button del_button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUserTheme(ParseUser.getCurrentUser().getInt("theme"));
        setContentView(R.layout.settings_view) ;
        del_button= findViewById(R.id.delAccount_btn);

        del_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(settingsActivity.this);
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("This will delete all your data from the account!\n Press ok to delete");
                alertDialog.setNegativeButton( "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ParseUser user= ParseUser.getCurrentUser();
                                String username= user.getUsername();

                                user.deleteInBackground(new DeleteCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e==null)
                                        {

                                            Intent intent = getIntent();
                                            intent.putExtra("delete",true);

                                            Toast.makeText(getBaseContext(), "Deleted", Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            Toast.makeText(getBaseContext(), "something went wrong "+e, Toast.LENGTH_LONG).show();

                                        }

                                    }
                                });


                                deleteUserMessage(username);
                                deleteUserCourse(username);
                                deleteUserQuiz(username);
                                deleteUserFriends(username);
                                deleteUserTodoList(username);
                                dialog.dismiss();
                                finishFromChild(settingsActivity.this);
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
            }
        });

    }

    public void deleteUserMessage(String username)
    {
        ParseQuery<ParseObject> query1= new ParseQuery<ParseObject>("Message");

        query1.whereEqualTo("sender",username);

        ParseQuery<ParseObject> query2= new ParseQuery<ParseObject>("Message");

        query2.whereEqualTo("recipient",username);

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

    public void deleteUserCourse(String username)
    {
        ParseQuery<ParseObject> userCourses= ParseQuery.getQuery("User_Courses");

        userCourses.whereEqualTo("username", username);
        userCourses.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects.size()>0 && e==null)
                {

                    for (ParseObject object:objects)
                    {

                        object.deleteInBackground();
                    }
                }
            }
        });
    }

    public void deleteUserFriends(String username)
    {
        ParseQuery<ParseObject> query1= new ParseQuery<ParseObject>("User_Friends");

        query1.whereEqualTo("user_name",username);

        ParseQuery<ParseObject> query2= new ParseQuery<ParseObject>("User_Friends");

        query2.whereEqualTo("user_friends",username);

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

    public void deleteUserQuiz(String username)
    {
        ParseQuery<ParseObject> query1= new ParseQuery<ParseObject>("Message");

        query1.whereEqualTo("sender",username);


        ParseQuery<ParseObject> query2= new ParseQuery<ParseObject>("Message");

        query2.whereEqualTo("recipient",username);

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

    public void deleteUserTodoList(String username)
    {
        ParseQuery<ParseObject> query1= new ParseQuery<ParseObject>("Practice_Quiz");

        query1.whereEqualTo("sender",username);


        ParseQuery<ParseObject> query2= new ParseQuery<ParseObject>("Practice_Quiz");

        query2.whereEqualTo("recipient",username);

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

    public void saveSettings(View view) {
        RadioGroup colors = findViewById(R.id.colorGroup);

        int color = colors.getCheckedRadioButtonId();

        if(color == R.id.radioButton2) {
            currentUser.put("theme", 0);
        }
        else if(color == R.id.radioButton3) {
            currentUser.put("theme", 1);
        }
        else if(color == R.id.radioButton4) {
            currentUser.put("theme", 2);
        }
        else if(color == R.id.radioButton5) {
            currentUser.put("theme", 3);
        }

        currentUser.saveInBackground();

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Back out of settings to display your changes");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

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


}
