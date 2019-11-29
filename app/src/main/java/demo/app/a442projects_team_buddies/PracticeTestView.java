package demo.app.a442projects_team_buddies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class PracticeTestView extends AppCompatActivity {

    private String activeUser;

    public ListView practiceTestListView;

    public ArrayList<String> arrayList;
    public ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_test_view);
        Intent intent= getIntent();
        activeUser= intent.getStringExtra("username");

        setTitle(activeUser);

        practiceTestListView= findViewById(R.id.practiceTestListView);
        arrayList= new ArrayList<>();
        arrayList.clear();
        arrayList.add("sample test");



        arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        practiceTestListView.setAdapter(arrayAdapter);

        practiceTestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent1 = new Intent(PracticeTestView.this, TakePracticeTest.class);
                intent1.putExtra("testName",arrayList.get(position));
                intent1.putExtra("username",activeUser);
                startActivity(intent1);


            }
        });

        ParseQuery<ParseObject> query= ParseQuery.getQuery("Practice_Quiz");
        query.whereEqualTo("sender", activeUser);
        query.whereEqualTo("recipient",ParseUser.getCurrentUser().getUsername());
        query.orderByAscending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e==null)
                {
                    if(objects.size()>0)
                    {
                        arrayList.clear();
                        String unique="";
                        for (ParseObject object :objects)
                        {
                            if(!object.getString("testName").equals(unique))
                            {
                                unique= object.getString("testName");
                                arrayList.add(unique);
                            }
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.sharefilemenu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent= new Intent(PracticeTestView.this, CreatePracticeTest.class);
        intent.putExtra("username",activeUser);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}

