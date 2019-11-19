package demo.app.a442projects_team_buddies;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ExampleViewHolder>{
    ArrayList<CourseViewItem> courseList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
            public TextView course;
            public TextView prof;
            public TextView studentCount;

            public ExampleViewHolder(@NonNull View itemView) {
                super(itemView);

                course = itemView.findViewById(R.id.nameOfClass);
                prof= itemView.findViewById(R.id.profName);
                studentCount=itemView.findViewById(R.id.numbOfStudents);
            }
    }

    public CardViewAdapter(ArrayList<CourseViewItem> courseList)
    {
            this.courseList=courseList;

        }

//    public CourseViewItem getCardAt(int position){
//        return courseList.get(position);
//    }
//    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_sample, parent,false);
            ExampleViewHolder evh = new ExampleViewHolder(v);
             return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position)
    {
                CourseViewItem currentItem=courseList.get(position);

                holder.course.setText(currentItem.getCourseName());
                holder.prof.setText(currentItem.getInstructor());
                holder.studentCount.setText(currentItem.getStudentCount());

    }

    @Override
    public int getItemCount() {

        return courseList.size();
    }



}

