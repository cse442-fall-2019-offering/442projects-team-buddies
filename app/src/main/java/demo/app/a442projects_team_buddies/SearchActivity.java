package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private SearchView mySearchView;
    private ListView myList;
    private ImageView addManually;

    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
    }

    public void init() {
        mySearchView = findViewById(R.id.searchView);
        myList = findViewById(R.id.myList);
        addManually = findViewById(R.id.addManually);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        myList.setAdapter(adapter);
        list = new ArrayList<String>();
        list.add("CSE 115: Introduction to Computer Science I");
        list.add("CSE 116: Introduction to Computer Science II");
        list.add("CSE 191: Discrete Structures");
        list.add("CSE 341: Computer Organization");
        list.add("CSE 368: Introduction to Artificial Intelligence");
        list.add("CSE 460: Data Models and Query Languages");
        list.add("CSE 474: Introduction to Machine Learning");
        list.add("CSE 486: Distributed Systems");

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }
    public void addCourseManually(){
           addManually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addClassManually = new Intent(SearchActivity.this, AddManuallyActivity.class);

                startActivity(addClassManually);
            }
        });
    }
}
