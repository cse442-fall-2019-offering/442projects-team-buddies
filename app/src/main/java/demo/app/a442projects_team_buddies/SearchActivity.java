package demo.app.a442projects_team_buddies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.telephony.CarrierConfigManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
//    private SearchView mySearchView;
//    private ListView myList;
//
//    private ArrayList<String> list;
//    private ArrayAdapter<String> adapter;

    private Intent intent;
    private ImageButton addClass;
    private SearchView searchView;
    private ListView courseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchView);
        courseListView = findViewById(R.id.myList);

        //searchView.getQuery();

        Toast.makeText(getBaseContext(), searchView.getQuery(), Toast.LENGTH_LONG).show();


        final ArrayList<String> arrayList= new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Course");
        query.whereStartsWith("Course_Number","CSE");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {

                    for (ParseObject object:scoreList)
                    {
                       // Toast.makeText(getBaseContext(), object.getString("Course_Number") , Toast.LENGTH_LONG).show();
                        arrayList.add(object.getString("Course_Number"));
                    }
                    //Toast.makeText(getBaseContext(), "Course"+scoreList.size() , Toast.LENGTH_LONG).show();
                } else {
                    //Log.i("Course", "Error: " + e.getMessage());

                    Toast.makeText(getBaseContext(), "Error: "  , Toast.LENGTH_LONG).show();
                }
            }
        });



        ArrayAdapter arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        courseListView.setAdapter(arrayAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;

            }
        });

        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), arrayList.get(position), Toast.LENGTH_LONG).show();
                //finishActivity(1);

                ParseObject userCourseEnrolled= new ParseObject("User_Courses");

                userCourseEnrolled.put("username", ParseUser.getCurrentUser().getUsername());
                userCourseEnrolled.put("Course_Number",arrayList.get(position));
                userCourseEnrolled.saveInBackground();


            }


        });



        //courseListView.setOnClickListen







        intent = new Intent(this, AddManuallyActivity.class);
        addClass = findViewById(R.id.addManually);
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Course");
                query.whereEqualTo("Course_Number","CSE 442");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            //Log.i("Course", "Retrieved " + scoreList.size() + " scores");
                            for (ParseObject object:scoreList)
                            {
                               //Toast.makeText(getBaseContext(), object.getString("Course_Number") , Toast.LENGTH_LONG).show();

                            }
                            //Toast.makeText(getBaseContext(), "Course"+scoreList.size() , Toast.LENGTH_LONG).show();
                        } else {
                            //Log.i("Course", "Error: " + e.getMessage());

                            Toast.makeText(getBaseContext(), "Error: "  , Toast.LENGTH_LONG).show();
                        }
                    }
                });
                startActivity(intent);
            }
        });



    }



    //I dont know what this is below but it made the thing crash

//    public void init() {
//        mySearchView = findViewById(R.id.searchView);
//        myList = findViewById(R.id.myList);
//        addManually = findViewById(R.id.addManually);
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
//        myList.setAdapter(adapter);
//        list = new ArrayList<String>();
//        list.add("CSE 115: Introduction to Computer Science I");
//        list.add("CSE 116: Introduction to Computer Science II");
//        list.add("CSE 191: Discrete Structures");
//        list.add("CSE 341: Computer Organization");
//        list.add("CSE 368: Introduction to Artificial Intelligence");
//        list.add("CSE 460: Data Models and Query Languages");
//        list.add("CSE 474: Introduction to Machine Learning");
//        list.add("CSE 486: Distributed Systems");
//
//        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                adapter.getFilter().filter(s);
//                return false;
//            }
//        });
//    }

}
