package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
//    private SearchView mySearchView;
//    private ListView myList;
//
//    private ArrayList<String> list;
//    private ArrayAdapter<String> adapter;

    private Intent intent;
    private ImageButton addClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        intent = new Intent(this, AddManuallyActivity.class);
        addClass = findViewById(R.id.addManually);
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
