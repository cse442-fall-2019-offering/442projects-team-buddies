package demo.app.a442projects_team_buddies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class StudentListFragment extends Fragment {

    String Id;
    Bitmap bitmap;

    private RecyclerView sView ;
    private StudentViewAdapter sViewAdapter;
    private RecyclerView.LayoutManager sViewLayoutManager;
    ArrayList<StudentViewItem> studentList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate1= inflater.inflate(R.layout.student_view,container,false);

        sView= inflate1.findViewById(R.id.recyclerView);

        sViewLayoutManager = new LinearLayoutManager(getContext());
        studentList= new ArrayList<StudentViewItem>();
        //bitmap= null;


        Id= this.getArguments().getString("Course").toString();


        ParseQuery<ParseObject> users= ParseQuery.getQuery("User_Courses");


        users.whereEqualTo("Course_Number",Id);

        users.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {


                if(e==null && objects.size()>0)
                {

                    for(ParseObject obj: objects) {
                       final String username= obj.getString("username");
                       if(!username.equals(ParseUser.getCurrentUser().getUsername())) {



                           ParseQuery<ParseUser> user = ParseUser.getQuery();

                           user.whereEqualTo("username",username);

                           user.findInBackground(new FindCallback<ParseUser>() {
                               @Override
                               public void done(List<ParseUser> objects, ParseException e) {
                                   if (e == null && objects.size() > 0) {
                                       for (final ParseUser user1 : objects) {

                                               //Toast.makeText(getContext(), " Pressed++++++++++++++++++++"+ParseUser.getCurrentUser().getUsername(), Toast.LENGTH_LONG).show();
                                           bitmap=null;


                                           try {


                                               ParseFile file = (ParseFile) user1.get("Profile_Image");


                                               file.getDataInBackground(new GetDataCallback() {
                                                   @Override
                                                   public void done(byte[] data, ParseException e) {


                                                       if (e == null && data != null) {
                                                           bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                                           Toast.makeText(getContext(), " Profile picture set", Toast.LENGTH_LONG).show();

                                                           studentList.add(new StudentViewItem(user1.getUsername(),bitmap));

                                                           //profileImage.setImageBitmap(bitmap);
                                                       } else {
                                                           Toast.makeText(getContext(), " Profile picture is not set", Toast.LENGTH_LONG).show();
                                                           studentList.add(new StudentViewItem(user1.getUsername(),bitmap));


                                                       }

                                                   }
                                               });

                                           }
                                           catch (Exception ex)
                                           {
                                               //Toast.makeText(getContext(), " Profile picture is not set"+ex, Toast.LENGTH_LONG).show();
                                               //bitmap= null;
                                               studentList.add(new StudentViewItem(user1.getUsername(),bitmap));

                                           }
                                           //studentList.add(new StudentViewItem(user1.getUsername().toString(),bitmap));





                                       }
                                       sView.setHasFixedSize(true);  // if the view changes in size then comment out this line
                                       sViewAdapter = new StudentViewAdapter(studentList);
                                       sView.setLayoutManager(sViewLayoutManager);
                                       sView.setAdapter(sViewAdapter);

                                       sViewAdapter.setOnItemClickListener(new StudentViewAdapter.OnItemClickListener() {
                                           @Override
                                           public void onItemClick(int position) {
                                               String s= studentList.get(position).getStudentName();

                                               Toast.makeText(getContext(), s + " Pressed", Toast.LENGTH_LONG).show();
                                               Intent intent= new Intent(getActivity(),sendFriendRequest.class);
                                               intent.putExtra("username",s);
                                               startActivity(intent);
                                           }
                                       });
                                   }
                                   else{
                                       Toast.makeText(getContext(), " Pressed++++++++++++++++++++"+username, Toast.LENGTH_LONG).show();

                                   }
                               }
                           });
                       }
                       else
                       {

                       }
                    }
                }
                else
                {
                    Toast.makeText(inflate1.getContext(), "no user enrolled in this course", Toast.LENGTH_LONG).show();

                }


            }
        });









        //ParseQuery<ParseObject> users= ParseQuery.getQuery("User_Courses");

        /*users.whereEqualTo("Course_Number",s);
        userCourses.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects.size()>0 && e==null)
                {

                    for (ParseObject object:objects)
                    {
                        //Toast.makeText(inflate1.getContext(), object.getString("Course_Number"), Toast.LENGTH_LONG).show();

                        final ParseQuery<ParseObject> userCourse= ParseQuery.getQuery("Course");
                        userCourse.whereEqualTo("Course_Number", object.getString("Course_Number"));

                        userCourse.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {
                                if(objects.size()>0 && e==null){

                                    ParseObject course= objects.get(0);

                                    courses.add(new CourseViewItem("Course Name: "+course.getString("Course_Name"),"Course no.: "+course.getString("Course_Number"),"Instructor: "+course.getString("Instructor")) );

                                    rView.setHasFixedSize(true);  // if the view changes in size then comment out this line

                                    rViewAdapter = new CardViewAdapter(courses);
                                    rView.setLayoutManager(rViewLayoutManager);
                                    rView.setAdapter(rViewAdapter);

                                    //this listener will redirect to Student profile those who are enrolled in that course
                                    rViewAdapter.setOnItemClickListener(new CardViewAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position) {
                                            String s= courses.get(position).getStudentCount();
                                            s= s.substring(12);
                                            Toast.makeText(getContext(), s+" Pressed", Toast.LENGTH_LONG).show();

                                            Intent intent= new Intent(getActivity(),TabActivity.class);
                                            intent.putExtra("Course",s);

                                            startActivity(intent);
                                        }
                                        //this will delete the cardView from the database
                                        @Override
                                        public void onDeleteClick(int position) {
                                            String s= courses.get(position).getStudentCount();
                                            s= s.substring(12);
                                            removeCourse(position);
                                            ParseQuery<ParseObject> userCourse= ParseQuery.getQuery("User_Courses");


                                            userCourse.whereEqualTo("Course_Number",s);
                                            userCourse.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());

                                            userCourse.findInBackground(new FindCallback<ParseObject>() {
                                                @Override
                                                public void done(List<ParseObject> objects, ParseException e) {
                                                    for (ParseObject obj:objects)
                                                    {
                                                        obj.deleteInBackground();



                                                    }
                                                }
                                            });


                                        }
                                    });
                                    //------------------------------------------------------------------------------------



                                }

                            }
                        });


                    }
                }
            }
        });*/







        return inflate1;
    }
}
