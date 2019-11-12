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


    private void loadImages(ParseFile thumbnail, final ImageView img) {
            try {

                if (thumbnail != null) {
                    thumbnail.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            if (e == null) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                     img.setImageBitmap(bmp); //or comment out next 2 line and use this
//                                BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp); //remove string.valueof and fix if it doesnt work
//                                img.setImageDrawable(bitmapDrawable);
                            } else {
                            }
                        }
                    });
                } else {
                    //img.setImageResource(R.drawable.photo_circle);
                }
            }
            catch (Exception e)
            {

            }
        }// load image








    public void getImages(ArrayList<Integer> images, ParseUser user1 ){
        //int i =0;
       // for(ParseObject id: objectids){
         //   String idToString = id.toString();
          // ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
            //query.getInBackground(objectids, new GetCallback<ParseObject>() {
              //  public void done(ParseObject object, ParseException e) {
                //    if (e == null) {
                  // //     object.getString("UserImage");  // might have to change field to int
                        try {
                            ParseFile image = user1.getParseFile("profile_image");
                            profImage = findViewById(R.id.icon); //R.id.profile_image

                            loadImages(image, profImage);
                          //  images.add(getDrawableId(profImage));
                            images.add(R.drawable.photo_circle);

                        }
                        catch (Exception e){


                        }
//                    }
//                }
//            });

           // i++;
        }

   // }





    public void addFriends(int size, List<ParseObject> friends ) {

        friendList = new ArrayList<>();
        images = new ArrayList<>();
        //// adding  friend names
        for (int i = 0; i < friends.size(); i++) {
            friendList.add(friends.get(i).getString("user_friends"));
             images.add(R.drawable.photo_circle);
        }

        ///////adding the friend pictures

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
//                     images = new Integer[fL.size()];
//                     friendList = new ArrayList<>();
//                   for(int i =0 ; i< fL.size() ; i++){
//                        images[i]= R.drawable.photo_circle;
//
//                        friendList.add("user"+(i+1));
//                    }







//            String userFriend= person.toString();
//            friendList[i]=userFriend;
                        //images[i]= R.drawable.photo_circle;  // needs to change for each user picture
                        // getImages(images, objectids);
                        //i++;





                    ListView lv = findViewById(R.id.messagesList);

                    MyListAdapter mlAdapter = new MyListAdapter(Messages.this,friendList,images);
                    lv.setAdapter(mlAdapter);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                        @Override
                        public void onItemClick (AdapterView < ? > adapterView, View view,int i, long l){

                            for (int x=0; x<friendList.size() ; x++) {
                                if(x==i){
                                    Intent intent = new Intent(Messages.this, MessagingRoom.class);
                                    startActivity(intent);
                                }
                            }

                        }

                    });
                }
//
            }

        });









    }
//

}
