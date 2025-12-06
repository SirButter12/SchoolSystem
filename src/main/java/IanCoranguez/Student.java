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
    /**
     * Creates a new student object, and generates an unique id with the format S000000
     * @param studentName the student name
     * @param gender student gender
     * @param address student address
     * @param department student department
     */
    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%06d", nextId++);
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
    }

    /**
     * registers a course in the student registered courses by calling the registerStudent method of the course object we want to register the student in
     * @param course the course we want to register the student in
     * @return true if it was succesfully registered, false if the student was already registered in the course
     */
    public boolean registerCourse(Course course){
        return course.registerStudent(this);
    }

    /**
     * eliminates a course from the student registeredCourses by calling the removeStudent method of the course object we want to eliminate the student from
     * @param course the course we want to eliminate the student from
     * @return true if it was succesfully removed, false if the student was not in the course in the first place
     */
    public boolean dropCourse(Course course) {
        return course.removeStudent(this);
    }

    /**
     * Describes the object as a string
     * @return the object described as a string
     */
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

    /**
     * A simplified version of toString this helps in other classes' toString method
     * @return the simplified string of the object
     */
    public String toSimplifiedString(){
        return String.format("Student{ name: %s id: %s department: %s }", studentName, studentId, department);
    }

    /**
     * enumerated the possible genders for the student
     */
    public enum Gender{
        MALE, FEMALE
    }

}
