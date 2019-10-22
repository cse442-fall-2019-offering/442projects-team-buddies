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

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
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

        arrayList.add("CSE$$");
        arrayList.add("CSE444");

        ArrayAdapter arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        courseListView.setAdapter(arrayAdapter);

        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), arrayList.get(position), Toast.LENGTH_LONG).show();
                finishActivity(1);


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
                                Toast.makeText(getBaseContext(), object.getString("Course_Number") , Toast.LENGTH_LONG).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(getBaseContext(),"finnishhhh", Toast.LENGTH_LONG).show();



    }

}
