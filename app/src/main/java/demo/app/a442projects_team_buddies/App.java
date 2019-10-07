package demo.app.a442projects_team_buddies;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("3626145656252fd2e04a7a5c43ef8353c71e6e5a")
                // if defined
                .clientKey("f2ce2c98ef747780d2b8536792131113182a7210")
                .server("http://18.220.52.221:80/parse/")
                .build());

        ParseObject gameScore = new ParseObject("GameScore");
        gameScore.put("score", 1337);
        gameScore.put("playerName", "Sean Plott");
        gameScore.put("cheatMode", false);
        gameScore.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Log.i("Parse result",e.toString());
            }
        });


    }
}
