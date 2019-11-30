package demo.app.a442projects_team_buddies;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
                                                   View item, final int pos, long id) {

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(todoListActivity.this);
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Confirm to delete the task");
                        alertDialog.setNegativeButton( "Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        items.remove(pos);
                                        itemsAdapter.notifyDataSetChanged();
                                        saveChanges();
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.setPositiveButton( "Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert= alertDialog.create();
                        alert.show();

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
