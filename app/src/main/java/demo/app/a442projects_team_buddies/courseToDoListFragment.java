package demo.app.a442projects_team_buddies;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class courseToDoListFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView toDoItems;
    private ParseUser user = ParseUser.getCurrentUser();
    private JSONArray todoList;

    private View inflater1;
    private String Id;
    private ParseObject toDoListObj;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflater1 = inflater.inflate(R.layout.todo_lists_view,container,false);

        Id =this.getArguments().getString("Course").toString();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("toDoLists");
        query = query.whereEqualTo("classID", Id);

        query = query.whereEqualTo("userID", user.getUsername());

        try {
            toDoListObj = query.getFirst();
        } catch (ParseException e) {
            toDoListObj = new ParseObject("toDoLists");
            toDoListObj.put("userID", user.getUsername());
            toDoListObj.put("classID", Id);
            toDoListObj.put("todoList", new JSONArray());
            toDoListObj.saveInBackground();
        }


        toDoItems = (ListView) inflater1.findViewById(R.id.todoItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(inflater1.getContext(), android.R.layout.simple_list_item_1, items);
        toDoItems.setAdapter(itemsAdapter);

        todoList = toDoListObj.getJSONArray("todoList");

        Button addToDo = inflater1.findViewById(R.id.btnAddToDoTask);

        //Adds the functionality to the add button
        addToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newTask = (EditText) inflater1.findViewById(R.id.editTextNewToDoListItem);
                String newTaskString = newTask.getText().toString();
                itemsAdapter.add(newTaskString);
                newTask.setText("");
                saveChanges();
            }
        });

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


        return inflater1;
    }

    private void setupListViewListener() {
        toDoItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, final int pos, long id) {

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(inflater1.getContext());
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

    private void saveChanges() {
        todoList = new JSONArray();
        for(String item : items) {
            todoList.put(item);
        }
        toDoListObj.put("todoList", todoList);
        toDoListObj.saveInBackground();
    }

}
