package demo.app.a442projects_team_buddies;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

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

        ParseObject.registerSubclass(Chatting.class);           // for messaging

        /************ chat application part ***********/
        // Use for monitoring Parse network traffic
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);
        /******  if errors occur then uncomment and adjust ********/
         /* // set applicationId and server based on the values in the Heroku settings.
         *         // any network interceptors must be added with the Configuration Builder given this syntax
         *         Parse.initialize(new Parse.Configuration.Builder(this)
         *              .applicationId("YOUR_APPLICATION_ID") // should correspond to APP_ID env variable
         *              .clientBuilder(builder)
         *              .server("https://myparseapp.herokuapp.com/parse/").build());
         */

    }
}
