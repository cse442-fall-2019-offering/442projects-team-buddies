
package demo.app.a442projects_team_buddies;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class CourseFragment extends Fragment {

    FloatingActionButton floatingActionButton;

    CardView cardView;
    LinearLayout linearLayout;


    private RecyclerView rView ;
    private RecyclerView.Adapter rViewAdapter;
    private RecyclerView.LayoutManager rViewLayoutManager;
    ArrayList<CourseViewItem> courses= new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate1= inflater.inflate(R.layout.activity_new_course_view,container,false);

        floatingActionButton= inflate1.findViewById(R.id.fab);

        //linearLayout= inflate1.findViewById(R.id.course_linear);

        //courses.add(new CourseViewItem("CSE115","197","MathewHertz") );

        rView= inflate1.findViewById(R.id.recyclerView);
        rView.setHasFixedSize(true);  // if the view changes in size then comment out this line
        rViewLayoutManager = new LinearLayoutManager(getContext());
        //rViewAdapter = new CardViewAdapter(courses);
        //rView.setLayoutManager(rViewLayoutManager);
        //rView.setAdapter(rViewAdapter);

        ParseQuery<ParseObject> userCourses= ParseQuery.getQuery("User_Courses");

        userCourses.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        userCourses.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects.size()>0 && e==null)
                {

                    for (ParseObject object:objects)
                    {
                        //Toast.makeText(inflate1.getContext(), object.getString("Course_Number"), Toast.LENGTH_LONG).show();

                        ParseQuery<ParseObject> userCourse= ParseQuery.getQuery("Course");
                        userCourse.whereEqualTo("Course_Number", object.getString("Course_Number"));

                        userCourse.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {
                                if(objects.size()>0 && e==null){

                                    ParseObject course= objects.get(0);

                                    courses.add(new CourseViewItem("Course Name: "+course.getString("Course_Name"),"Course no.: "+course.getString("Course_Number"),"Instructor: "+course.getString("Instructor")) );


                                    rView.setHasFixedSize(true);  // if the view changes in size then comment out this line
                                    //rViewLayoutManager = new LinearLayoutManager();
                                    rViewAdapter = new CardViewAdapter(courses);
                                    rView.setLayoutManager(rViewLayoutManager);
                                    rView.setAdapter(rViewAdapter);

                                }

                            }
                        });


                    }
                }
            }
        });





        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(inflate1.getContext(), "Fab button pressed", Toast.LENGTH_LONG).show();
                //cardView= new CardView(inflate1.getContext());
                //cardView= inflate1.findViewById(R.id.cardview);
                //cardView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


                //cardView.setCardBackgroundColor(Color.BLUE);
                //linearLayout.addView(cardView);



                Intent intent= new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);

                Fragment frg = null;
                frg = getFragmentManager().findFragmentByTag("course tag");
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(frg);
                ft.attach(frg);
                ft.commit();






            }
        });

        return inflate1;
    }
}