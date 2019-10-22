package demo.app.a442projects_team_buddies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NewCourseViewActivity extends AppCompatActivity {
    private RecyclerView rView ;
    private RecyclerView.Adapter rViewAdapter;
    private RecyclerView.LayoutManager rViewLayoutManager;
   private FloatingActionButton floatingActionButton;
    private ArrayList<CourseViewItem>  courses= new ArrayList<>();
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course_view);


        floatingActionButton= findViewById(R.id.fab);

     //   courses.add(new CourseViewItem("CSE115","197","MathewHertz") );

        floatingActionButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Fab button pressed", Toast.LENGTH_LONG).show();
                createCourseInfoList();
               createRecyclerView();

            }


        });

            itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //courses.remove(viewHolder.getAdapterPosition());
                removeItem(viewHolder.getAdapterPosition());
            }

        };



    }


    public void removeItem(int position){   /// can make unit test for this
        courses.remove(position);
        rViewAdapter.notifyItemRemoved(position);
    }







    public void createCourseInfoList(){
        courses.add(new CourseViewItem("CSE250","350","MathewHertz") );
    }
    public void createRecyclerView(){


        rView=findViewById(R.id.recyclerView);
        rView.setHasFixedSize(true);  // if the view changes in size then comment out this line
        rViewLayoutManager = new LinearLayoutManager(this);
        rViewAdapter = new CardViewAdapter(courses);
        rView.setLayoutManager(rViewLayoutManager);
        rView.setAdapter(rViewAdapter);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rView);
    }
}
