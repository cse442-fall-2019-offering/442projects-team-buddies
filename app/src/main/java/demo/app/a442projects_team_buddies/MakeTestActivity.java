package demo.app.a442projects_team_buddies;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class MakeTestActivity extends AppCompatActivity {

    EditText textIn;
    Button buttonAdd,button;
    LinearLayout container;
    TextView textOut;
    View addView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textIn = findViewById(R.id.editText);
        buttonAdd = findViewById(R.id.add);
        container = findViewById(R.id.container);
        button = findViewById(R.id.btn_generate);

        buttonAdd.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                addView = layoutInflater.inflate(R.layout.row, null);
                textOut= addView.findViewById(R.id.question_field);
                textOut.setText(textIn.getText().toString());
                textIn.setText(null);

                Button buttonRemove = addView.findViewById(R.id.remove);
                buttonRemove.setOnClickListener(new OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        ((LinearLayout)addView.getParent()).removeView(addView);
                    }});

                container.addView(addView);
            }});


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }
    public void openDialog() {
        GenerateNewTest exampleDialog = new GenerateNewTest();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

}
