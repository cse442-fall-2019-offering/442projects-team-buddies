package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;
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
    FloatingActionButton floatingActionButton;
    ArrayList<CourseViewItem>  courses= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course_view);






        floatingActionButton= findViewById(R.id.fab);

        courses.add(new CourseViewItem("CSE115","197","MathewHertz") );



        rView=findViewById(R.id.recyclerView);
        rView.setHasFixedSize(true);  // if the view changes in size then comment out this line
        rViewLayoutManager = new LinearLayoutManager(this);
        rViewAdapter = new CardViewAdapter(courses);
        rView.setLayoutManager(rViewLayoutManager);
        rView.setAdapter(rViewAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Fab button pressed", Toast.LENGTH_LONG).show();
                //courses.add(new CourseViewItem("CSE115","197","MathewHertz") );
                //courses.add(new CourseViewItem("CSE116","107","MathewHertz") );
                courses.add(new CourseViewItem("CSE250","350","MathewHertz") );

                rView=findViewById(R.id.recyclerView);
                rView.setHasFixedSize(true);  // if the view changes in size then comment out this line
                //rViewLayoutManager = new LinearLayoutManager();
                rViewAdapter = new CardViewAdapter(courses);
                rView.setLayoutManager(rViewLayoutManager);
                rView.setAdapter(rViewAdapter);
            }

        });

    }
}
