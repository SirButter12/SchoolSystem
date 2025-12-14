package IanCoranguez;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Represents a student in the system.
 * Each student has a unique ID, name, gender, address, department,
 * and a list of registered courses.
 */
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

    /**
     * Creates a new Student object and generates a unique ID in the format S000000.
     *
     * @param studentName the student's full name
     * @param gender      the student's gender
     * @param address     the student's address
     * @param department  the student's department
     */
    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%06d", nextId++);
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
    }

    /**
     * Registers this student in a course by calling the course's registerStudent method.
     *
     * @param course the course to register the student in
     * @return true if the student was successfully registered, false if already registered
     */
    public boolean registerCourse(Course course) {
        return course.registerStudent(this);
    }

    /**
     * Drops this student from a course by calling the course's removeStudent method.
     *
     * @param course the course to drop the student from
     * @return true if the student was successfully removed, false if not enrolled
     */
    public boolean dropCourse(Course course) {
        return course.removeStudent(this);
    }

    /**
     * Returns a detailed string representation of the student, including registered courses.
     *
     * @return a string describing the student
     */
    public String toString() {
        String courses = "";
        if (!registeredCourses.isEmpty()) {
            for (Course registeredCourse: registeredCourses) {
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
     * Returns a simplified string representation of the student.
     * This is used for concise display in other classes' toString methods.
     *
     * @return a simplified string describing the student
     */
    public String toSimplifiedString() {
        return String.format("Student{ name: %s id: %s department: %s }", studentName, studentId, department);
    }

    /**
     * Enumeration representing all the possible genders of a student.
     */
    public enum Gender {
        MALE, FEMALE
    }

}
