package demo.app.a442projects_team_buddies;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;


public class GenerateNewTest extends AppCompatDialogFragment {
    private EditText fileName;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.save_file_dialog, null);
        fileName = view.findViewById(R.id.file_name);
        builder.setView(view)
                .setTitle("Save file as  ")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int j) {
                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // save the data within this file name and upload it to the back end
                                String savedFileName = fileName.getText().toString();
                                // ** I need to save this fileName at somewhere, then I have to retrieve it at the practicing a practice test UI

                            }

                        }

                );

        return builder.create();
    }
}

