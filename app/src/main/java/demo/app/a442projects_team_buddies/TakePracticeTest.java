package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class TakePracticeTest extends AppCompatActivity {

    private TextView questionTextView;
    private TextView scoreTextView;
    private RadioGroup buttonGroups;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;
    private RadioButton option4;
    private Button checkButton;
    private Button nextButton;
    private RadioButton option;
    int score=0;
    int count=0;
    String activeUser;
    String solution;
    int correctAnsCount=0;


    int questionCount;
    List<ParseObject> questions= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_practice_test);
        setUserTheme(ParseUser.getCurrentUser().getInt("theme"));

        Intent intent = getIntent();
        String testName=intent.getStringExtra("testName");
        activeUser= intent.getStringExtra("username");
        setTitle(testName);

        questionTextView= findViewById(R.id.question_textView);
        scoreTextView= findViewById(R.id.score_textView);
        buttonGroups= findViewById(R.id.radios);
        option1= findViewById(R.id.option1);
        option2= findViewById(R.id.option2);
        option3= findViewById(R.id.option3);
        option4= findViewById(R.id.option4);
        checkButton= findViewById(R.id.checkAnswer_Button);
        nextButton= findViewById(R.id.nextQuestion_Button);




        ParseQuery<ParseObject> query= ParseQuery.getQuery("Practice_Quiz");
        query.whereEqualTo("sender", activeUser);
        query.whereEqualTo("recipient", ParseUser.getCurrentUser().getUsername());
        query.whereEqualTo("testName",testName);
        query.orderByAscending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null)
                {
                    if (objects.size()>0)
                    {
                        questionCount= objects.size();
                        //questions= new ArrayList<>();
                        questions= objects;
                        scoreTextView.setText("Score: "+"0/"+questionCount);
                        Toast.makeText(getBaseContext(),Integer.toString(questions.size()) , Toast.LENGTH_LONG).show();

                        setQuestion();
                    }
                }
            }
        });




        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    option = findViewById(buttonGroups.getCheckedRadioButtonId());

                    String s = (String) option.getTag();

                    if (s.equals(solution)) {
                        if(score < count && correctAnsCount==0) {
                            score++;
                            scoreTextView.setText("Score: " + score + "/" + questionCount);
                        }
                        Toast.makeText(getBaseContext(),  "Hurry! Correct answer", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        correctAnsCount= 1;
                        Toast.makeText(getBaseContext(),  "Wrong answer", Toast.LENGTH_LONG).show();

                    }


                }
                catch (Exception e)
                {
                    Toast.makeText(getBaseContext(), "Please select option", Toast.LENGTH_LONG).show();

                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>=questionCount)
                {
                    Toast.makeText(getBaseContext(), "Well done! you have completed this quiz", Toast.LENGTH_LONG).show();

                    finish();
                }
                else {
                    correctAnsCount=0;
                    setQuestion();
                }
            }
        });

    }
    public void setUserTheme(int theme) {
        if(theme == 0) {
            setTheme(R.style.defaultAppTheme);
        }
        else if(theme == 1) {
            setTheme(R.style.redAppTheme);
        }
        else if(theme == 2) {
            setTheme(R.style.greenAppTheme);
        }
        else if(theme == 3) {
            setTheme(R.style.yellowAppTheme);
        }
    }

    public void setQuestion()
    {

        ParseObject object= questions.get(count);

        count++;
        questionTextView.setText(count+". "+object.getString("question")+"?");
        option1.setText(object.getString("option1"));
        option2.setText(object.getString("option2"));
        option3.setText(object.getString("option3"));
        option4.setText(object.getString("option4"));
        solution= object.getString("answer");
    }
}
