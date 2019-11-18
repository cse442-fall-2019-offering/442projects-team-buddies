package demo.app.a442projects_team_buddies;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentViewAdapter extends RecyclerView.Adapter<StudentViewAdapter.ExampleViewHolder>{
    ArrayList<StudentViewItem> studentList;

    private OnItemClickListener mListner;

    // method to handle cardView listener in CourseView
    public interface OnItemClickListener{
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListner= listener;
    }
    //---------------------------------------------------

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
            public TextView username;


            public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
                super(itemView);

                username = itemView.findViewById(R.id.username);


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

            }
    }

    public StudentViewAdapter(ArrayList<StudentViewItem> studentList)
    {
            this.studentList=studentList;

    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentview_sample, parent,false);
            ExampleViewHolder evh = new ExampleViewHolder(v,mListner);
             return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position)
    {
                StudentViewItem currentItem=studentList.get(position);

                holder.username.setText(currentItem.getStudentName());


    }

    @Override
    public int getItemCount() {

        return studentList.size();
    }
}

