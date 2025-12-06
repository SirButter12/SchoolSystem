package IanCoranguez;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
public class Student {
    //fields
    @Getter
    private String studentId;
    @Getter @Setter
    private String studentName;
    @Getter
    private Gender gender;
    @Getter @Setter
    private Address address;
    @Getter @Setter
    private Department department;
    @Getter
    private ArrayList<Course> registeredCourses = new ArrayList<>();
    private static int nextId = 0;

    //methods
    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%06d", nextId++);
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
    }

    public boolean registerCourse(Course course){
        return course.registerStudent(this);
    }

    public boolean dropCourse(Course course) {
        return course.removeStudent(this);
    }

    public String toSimplifiedString(){
        return String.format("Student{ name: %s id: %s department: %s }", studentName, studentId, department);
    }

    public String toString(){
        String courses = "";
        if (!registeredCourses.isEmpty()){
            for (Course registeredCourse: registeredCourses){
                courses += registeredCourse.toSimplifiedString() + "\n";
            }
        }
        return String.format("Student { \n" +
                "id = %s \n" +
                "name = %s \n" +
                "gender = %s \n" +
                "address = %s \n" +
                "department = %s \n" +
                "registered courses: \n" +
                "%s \n" +
                "}", studentId, studentName, gender, address, department, courses);
    }

    public enum Gender{
        MALE, FEMALE
    }

}
