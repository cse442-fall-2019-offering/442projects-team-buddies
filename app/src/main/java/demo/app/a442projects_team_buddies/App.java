package demo.app.a442projects_team_buddies;

import android.app.Application;
import android.util.Log;

import com.parse.AuthenticationCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Map;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("18f0b8b3f64fc2d7621627538afa4c34c30c1e95")
                // if defined
                .clientKey("f4d5911c96f8e3e047845c9ee4bf6e4da09284f6")
                .server("http://3.15.229.128:80/parse/")
                .build());

        ParseInstallation.getCurrentInstallation().saveInBackground();


        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);





    }
}
