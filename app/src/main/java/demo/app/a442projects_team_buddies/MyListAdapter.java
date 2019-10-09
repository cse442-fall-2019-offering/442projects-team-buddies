package demo.app.a442projects_team_buddies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyListAdapter extends ArrayAdapter<String> {
    private final Activity users;
    private final String[] progNames;
    private final Integer[] progImages;


    public MyListAdapter(Activity users, String[] progNames, Integer[] progImages) {
        super(users, R.layout.message_list,progNames);
        this.users = users;
        this.progNames = progNames;
        this.progImages = progImages;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater infLater = users.getLayoutInflater();
        View rowView = infLater.inflate(R.layout.message_list,null,true);
        TextView txtName = (TextView)(rowView.findViewById(R.id.userInList));
        ImageView usrImage =(ImageView)(rowView.findViewById(R.id.userImageInList));

        txtName.setText(progNames[position]);
        usrImage.setImageResource(progImages[position]);
        return rowView;
    }
}
