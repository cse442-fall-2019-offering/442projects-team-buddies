package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class AddManuallyActivity extends AppCompatActivity {
    private ParseUser cur_user;
    private ParseObject enrolled_Course;
    private Button add_course;
    private EditText txt_ClassNum, txt_ClassName, txt_Professor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manually);
        cur_user = ParseUser.getCurrentUser();
        enrolled_Course = new ParseObject("User_Courses");
        txt_ClassNum = findViewById(R.id.txt_cNum);
        add_course = findViewById(R.id.btn_joinClass);
        add_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAddedCourseToBackEnd();
            }
        });
    }

    public void sendAddedCourseToBackEnd() {
        String classNum;
        classNum = txt_ClassNum.getText().toString();
        if (!classNum.isEmpty()) {
            cur_user.put("Courses", classNum);

        }
        // bugs
        //sendToCourseObj(cur_user.getString(classNum),enrolled_Course);

        cur_user.saveInBackground();
        Intent intent = new Intent(this, CourseViewActivity.class);
        startActivity(intent);
    }

    // **** this method crashes the app, gotta take care of it later
    public void sendToCourseObj(String addCourse,ParseObject enrolled_Class){
          enrolled_Class.put("username",cur_user.getUsername());
          enrolled_Class.put("Course_Number",addCourse);
          enrolled_Class.saveInBackground();
    }
}

