package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Messages extends AppCompatActivity {
   // Toolbar toolbar;
    ArrayList<String> friendList;
    ArrayList<Integer> images;
    ImageView profImage;
   int x;

    private Integer getDrawableId(ImageView iv) {
        return (Integer) iv.getTag();
    }






    public void addFriends(int size, List<ParseObject> friends ) {

        friendList = new ArrayList<>();
        images = new ArrayList<>();
        //// adding  friend names
        for (int i = 0; i < friends.size(); i++) {
            friendList.add(friends.get(i).getString("user_friends"));
             images.add(R.drawable.photo_circle);
        }

        /***********adding the friend pictures functioanlity here vikram *************/
                    //add here
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);


        ParseQuery<ParseObject> userFriends= ParseQuery.getQuery("User_Friends");
        userFriends.whereEqualTo("user_name",   ParseUser.getCurrentUser().getUsername() );
        userFriends.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> fL, ParseException e) {
                if (e == null) {

                        addFriends(fL.size(),fL);






                    ListView lv = findViewById(R.id.messagesList);

                    MyListAdapter mlAdapter = new MyListAdapter(Messages.this,friendList,images);
                    lv.setAdapter(mlAdapter);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                        @Override
                        public void onItemClick (AdapterView < ? > adapterView, View view,int i, long l){

//                            for (int x=0; x<friendList.size() ; x++) {
//                                if(x==i){
                                    Intent intent = new Intent(Messages.this, MessagingRoom.class);
                                    String friendname = friendList.get(i);
                                    intent.putExtra("friendsObjectId",friendname);
                                    startActivity(intent);
//                                }
//                            }

                        }

                    });
                }
//
            }

        });









    }
//

}
