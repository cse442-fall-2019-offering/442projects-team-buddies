package demo.app.a442projects_team_buddies;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class settingsActivity extends AppCompatActivity {

    ParseUser currentUser = ParseUser.getCurrentUser();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_view) ;

    }

    public void saveSettings(View view) {
        RadioGroup colors = findViewById(R.id.colorGroup);

        int color = colors.getCheckedRadioButtonId();

        switch (color) {
            case R.id.radioButton2:
                currentUser.put("theme", 0);
                break;

            case R.id.radioButton3:
                currentUser.put("theme", 1);
                break;

            case R.id.radioButton4:
                currentUser.put("theme", 2);
                break;

            case R.id.radioButton5:
                currentUser.put("theme", 3);
                break;
        }

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Restart the app to display your changes");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }



}
