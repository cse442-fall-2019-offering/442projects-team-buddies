package demo.app.a442projects_team_buddies;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ExampleViewHolder>{
    ArrayList<CourseViewItem> courseList;

    private OnItemClickListener mListner;

    // method to handle cardView listener in CourseView
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListner= listener;
    }
    //---------------------------------------------------

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
            public TextView course;
            public TextView prof;
            public TextView studentCount;
            public ImageView deleteImage;

            public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
                super(itemView);

                course = itemView.findViewById(R.id.nameOfClass);
                prof= itemView.findViewById(R.id.profName);
                studentCount=itemView.findViewById(R.id.numbOfStudents);
                deleteImage= itemView.findViewById(R.id.deleteCourse);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(listener!= null)
                        {
                            int position= getAdapterPosition();
                            if (position!= RecyclerView.NO_POSITION)
                            {
                                listener.onItemClick(position);
                            }
                        }
                    }
                });
                deleteImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(listener!= null)
                        {
                            int position= getAdapterPosition();
                            if (position!= RecyclerView.NO_POSITION)
                            {
                                listener.onDeleteClick(position);
                            }
                        }
                    }
                });

            }
    }

    public CardViewAdapter(ArrayList<CourseViewItem> courseList)
    {
            this.courseList=courseList;

    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_sample, parent,false);
            ExampleViewHolder evh = new ExampleViewHolder(v,mListner);
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

