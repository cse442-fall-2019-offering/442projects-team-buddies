
package demo.app.a442projects_team_buddies;

import android.graphics.Color;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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





        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(inflate1.getContext(), "Fab button pressed", Toast.LENGTH_LONG).show();
                //cardView= new CardView(inflate1.getContext());
                //cardView= inflate1.findViewById(R.id.cardview);
                //cardView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


                //cardView.setCardBackgroundColor(Color.BLUE);
                //linearLayout.addView(cardView);

                courses.add(new CourseViewItem("CSE250","350","MathewHertz") );


                rView.setHasFixedSize(true);  // if the view changes in size then comment out this line
                //rViewLayoutManager = new LinearLayoutManager();
                rViewAdapter = new CardViewAdapter(courses);
                rView.setLayoutManager(rViewLayoutManager);
                rView.setAdapter(rViewAdapter);
            }
        });

        return inflate1;
    }
}