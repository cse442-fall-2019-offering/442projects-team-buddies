package demo.app.a442projects_team_buddies;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class todoListActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView toDoItems;
    private ParseUser user = ParseUser.getCurrentUser();
    private JSONArray todoList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_lists_view);
        toDoItems = (ListView) findViewById(R.id.todoItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        toDoItems.setAdapter(itemsAdapter);

        todoList = user.getJSONArray("todoList");

        if(todoList != null) {
            try {
                for(int i = 0; i < todoList.length(); i++) {
                    items.add(todoList.getString(i));
                }
            }
            catch (JSONException e) {

            }
        }

        setupListViewListener();
    }

    public void addTodoItem(View view) {
        EditText newTask = (EditText) findViewById(R.id.editTextNewToDoListItem);
        String newTaskString = newTask.getText().toString();
        itemsAdapter.add(newTaskString);
        newTask.setText("");
        saveChanges();
    }

    private void setupListViewListener() {
        toDoItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();
                        saveChanges();
                        return true;
                    }

                });
    }

    public void onTodoBack(View view) {

        finish();
    }

    private void saveChanges() {
        todoList = new JSONArray();
        for(String item : items) {
            todoList.put(item);
        }
        user.put("todoList", todoList);
        user.saveInBackground();
    }


}
