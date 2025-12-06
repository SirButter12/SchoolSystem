package IanCoranguez;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode
public class Student {
    //fields
    //   1. `String studentId`   // 6-digits starts with a character `S`. This id should be increased automatically.
    private String studentId;
    //   2. `String studentName`
    private String studentName;
    //   3. `Gender gender`      // MALE FEMALE
    private Gender gender;
    //   4. `Address address`
    private Address address;
    //   5. `Department department`
    private Department department;
    //   6. `ArrayList<Course> registeredCourses`
    private ArrayList<Course> registeredCourses = new ArrayList<>();
    //   7. `static int nextId`  // indicates the next ID that will be used
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
        if (course.registerStudent(this)){
            registeredCourses.add(course);
            return true;
        }

        return false;
    }

    public boolean dropCourse(Course course) {
        if (course.removeStudent(this)){
            registeredCourses.remove(course);
            return true;
        }
        return false;
    }

    /*   4. `toSimplifiedString` // converts a student to a simple string with only the `studentId`, the `studentName`, and `departmentName`.
     This method is called in `Course` `toString()`.
     */

    /*   5. `toString` // converts a student to a string that contains the `studentId`, the `studentName`, the `gender`, the `address` and the `department`,
     and the `registeredCourses`
    (only the `courseId`, the `courseName`, and the `departmentName`)
     */

    //   6. equals
    //   7. getters
    //   8. setters
    public enum Gender{
        MALE, FEMALE
    }

}
