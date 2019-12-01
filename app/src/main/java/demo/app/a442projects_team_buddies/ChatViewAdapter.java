package demo.app.a442projects_team_buddies;


import android.graphics.Color;
import android.text.Html;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.view.Gravity.RIGHT;
import static android.view.Gravity.getAbsoluteGravity;

public class ChatViewAdapter extends RecyclerView.Adapter<ChatViewAdapter.ExampleViewHolder>{
    ArrayList<ChatViewItem> messageList;



    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
            public TextView message;
            public CardView messageCard;
            //public CircleImageView profileImage;


            public ExampleViewHolder(@NonNull View itemView) {
                super(itemView);

                message = itemView.findViewById(R.id.messageSample);
                messageCard= itemView.findViewById(R.id.messageCard);
                //profileImage= itemView.findViewById(R.id.profile);




            }
    }

    public ChatViewAdapter(ArrayList<ChatViewItem> messageList)
    {
            this.messageList = messageList;

    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.messageview_sample, parent,false);
            ExampleViewHolder evh = new ExampleViewHolder(v);
             return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position)
    {
                ChatViewItem currentItem=messageList.get(position);
                try {


                    holder.message.setText(currentItem.getMessage());
                    if(currentItem.getStatus().equals("right"))
                    {
                        //holder.message.setGravity(RIGHT);

                        String tag="<b>"+"<font color="+"#33FFEB"+">"+"ME|"+"</font>"+"</b>"+"<br>"+currentItem.getMessage();
                        holder.message.setText(Html.fromHtml(tag));
                    }
                    else
                    {

                        String tag="<b>"+"<font color="+"#FF3339"+">"+currentItem.getStatus()+"|"+"</font>"+"</b>"+"<br>"+currentItem.getMessage();
                        holder.message.setText(Html.fromHtml(tag));
                    }

                }
                catch (Exception e)
                {

                }


    }

    @Override
    public int getItemCount() {

        return messageList.size();
    }
}

