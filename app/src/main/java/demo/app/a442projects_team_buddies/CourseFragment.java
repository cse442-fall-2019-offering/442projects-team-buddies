
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CourseFragment extends Fragment {

    FloatingActionButton floatingActionButton;

    CardView cardView;
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate1= inflater.inflate(R.layout.courseview_page,container,false);

        floatingActionButton= inflate1.findViewById(R.id.fab);

        linearLayout= inflate1.findViewById(R.id.course_linear);






        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(inflate1.getContext(), "Fab button pressed", Toast.LENGTH_LONG).show();
                //cardView= new CardView(inflate1.getContext());
                //cardView= inflate1.findViewById(R.id.cardview);
                //cardView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


                //cardView.setCardBackgroundColor(Color.BLUE);
                //linearLayout.addView(cardView);
            }
        });

        return inflate1;
    }
}