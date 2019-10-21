package demo.app.a442projects_team_buddies;

public class CourseViewItem {
    private String courseName;
    private String studentCount;
    private String instructor;

    public CourseViewItem(String courseName, String studentCount,String instructor){
        this.courseName=courseName;
        this.studentCount=studentCount;
        this.instructor=instructor;

    }

    public String getCourseName() {
        return courseName;
    }

    public String getStudentCount() {
        return studentCount;
    }

    public String getInstructor() {
        return instructor;
    }
}
