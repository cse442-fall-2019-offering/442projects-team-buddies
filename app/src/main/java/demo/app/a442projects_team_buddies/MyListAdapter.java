package demo.app.a442projects_team_buddies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyListAdapter extends ArrayAdapter<String> {
    private final Activity users;
    private final ArrayList<String> progNames;
    private final ArrayList<Integer> progImages;



    public MyListAdapter(Activity users, ArrayList<String> progNames, ArrayList<Integer> progImages) {
        super(users, R.layout.message_list,progNames);
        this.users = users;
        this.progNames = progNames;
        this.progImages = progImages;
   //     this.x = x;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater infLater = users.getLayoutInflater();
        View rowView = infLater.inflate(R.layout.message_list,null,true);
        TextView txtName = (TextView)(rowView.findViewById(R.id.userInList));
        ImageView usrImage =(ImageView)(rowView.findViewById(R.id.userImageInList));

        txtName.setText(progNames.get(position));
        usrImage.setImageResource(progImages.get(position));
        return rowView;
    }
}
