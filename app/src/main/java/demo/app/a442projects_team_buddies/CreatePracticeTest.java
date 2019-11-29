package demo.app.a442projects_team_buddies;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class CreatePracticeTest extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText testName;
    private EditText question;
    private EditText option1;
    private EditText option2;
    private EditText option3;
    private EditText option4;
    private Button share;
    private Button nextQuestion;
    private Button done;
    private String solution;
    private int questionCount;

    String activeUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_practice_test);

        testName= findViewById(R.id.testName_EditText);
        question= findViewById(R.id.question_EditText);
        option1= findViewById(R.id.option1_EditText);
        option2= findViewById(R.id.option2_EditText);
        option3= findViewById(R.id.option3_EditText);
        option4= findViewById(R.id.option4_EditText);
        share= findViewById(R.id.share_Button);
        nextQuestion= findViewById(R.id.nextQuestion_Button);
        done= findViewById(R.id.done_Button);
        questionCount=0;




        Intent intent= getIntent();
        activeUser= intent.getStringExtra("username");

        setTitle("Share with: "+activeUser);





        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share();
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next_Question();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        Spinner spinner= findViewById(R.id.option_spinner);
        ArrayAdapter<CharSequence> arrayAdapter= ArrayAdapter.createFromResource(this,R.array.options,android.R.layout.simple_spinner_item);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(this);
    }

    public void Share()

    {


        if (testName.getText().toString().trim().equals("") || question.getText().toString().trim().equals("") || option1.getText().toString().trim().equals("") || option2.getText().toString().trim().equals("") || option3.getText().toString().trim().equals("") || option4.getText().toString().trim().equals("") )
        {

            Toast.makeText(getBaseContext(), "please enter all the field", Toast.LENGTH_LONG).show();


        }
        else
        {
            //save data on server;
            questionCount++;

            ParseQuery<ParseObject> query= ParseQuery.getQuery("Practice_Quiz");
            query.whereEqualTo("sender", ParseUser.getCurrentUser().getUsername());
            query.whereEqualTo("recipient",activeUser);

            query.whereEqualTo("testName",testName.getText().toString());

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e==null)
                    {
                        if(objects.size()>0)
                        {
                            Toast.makeText(getBaseContext(), "Quiz with given test name already exits", Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            ParseObject saveQuestion= new ParseObject("Practice_Quiz");
                            saveQuestion.put("sender", ParseUser.getCurrentUser().getUsername());
                            saveQuestion.put("recipient",activeUser);
                            saveQuestion.put("testName",testName.getText().toString());
                            saveQuestion.put("question",question.getText().toString());
                            saveQuestion.put("option1",option1.getText().toString());
                            saveQuestion.put("option2",option2.getText().toString());
                            saveQuestion.put("option3",option3.getText().toString());
                            saveQuestion.put("option4",option4.getText().toString());
                            saveQuestion.put("answer",solution);

                            saveQuestion.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if(e!=null)
                                    {
                                        Toast.makeText(getBaseContext(), "try again! something went wrong", Toast.LENGTH_LONG).show();

                                    }
                                    else
                                    {
                                        Toast.makeText(getBaseContext(), "Shared!", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                        }
                    }
                }
            });




        }
    }

    public void next_Question()
    {
        if (!testName.getText().toString().trim().equals("")) {
            testName.setVisibility(View.INVISIBLE);

            question.setText("");
            option1.setText("");
            option2.setText("");
            option3.setText("");
            option4.setText("");
        }
        else{
            Toast.makeText(getBaseContext(), "please enter test name first", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String string= (String) parent.getItemAtPosition(position);
        solution= string;

        Toast.makeText(getBaseContext(), string+" pressed", Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

