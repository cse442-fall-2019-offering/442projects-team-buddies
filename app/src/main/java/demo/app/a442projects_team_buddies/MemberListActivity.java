package demo.app.a442projects_team_buddies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class MemberListActivity extends AppCompatActivity {
    private ListView studentList;
    private ArrayList<String> enrolledStudents;
    private ArrayAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setTitle("Hello Android");
        setContentView(R.layout.activity_member_list);

        studentList = findViewById(R.id.mList);
        enrolledStudents = new ArrayList<>();

        // get the course name, go to this course , get all the enrollment students in that course
        ParseQuery<ParseObject> userCourses= ParseQuery.getQuery("User_Courses");

        userCourses.whereEqualTo("Course_Number", ParseUser.getCurrentUser().getClassName());
        userCourses.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects.size()>0 && e==null)
                {
                    // do it one by one
                    for (ParseObject object:objects)
                    {
                        // pull out a list of username where users' curr_course_num = this.course_num
                        ParseQuery<ParseObject> userCourse= ParseQuery.getQuery("Course");
                        userCourse.whereEqualTo("Course_Number", object.getString("Course_Number"));
                        userCourse.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {
                                if(objects.size()>0 && e==null){

                                    ParseObject course= objects.get(0);
                                    // add username to arrayList
                                    enrolledStudents.add(course.getString("username"));
                                }

                            }
                        });
                    }
                }
            }
        });

        // Goes to the sendFriendRequest.XML

        // Create a adapter
        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,enrolledStudents);
        // Assign adapter to mListView
        studentList.setAdapter(mAdapter);
        // Add action listener to the item on the list view, so it can incur cur_user to another UI where cur_user can send a friend request to another user
        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Start a new Intent by passing selected username
                Intent sendFriendRequest = new Intent(MemberListActivity.this,sendFriendRequest.class);
                sendFriendRequest.putExtra("username",enrolledStudents.get(i));
                startActivity(sendFriendRequest);
            }
        });
    }
}
