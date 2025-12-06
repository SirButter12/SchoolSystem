package IanCoranguez;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@EqualsAndHashCode
public class Course {
    //fields
    //   1. `String courseId` // This `twoDigitCourseId` should be increased automatically, the id follows the style `C-departmentId-twoDigitCourseId`, e.g.: `C-D01-01`, `C-D01-05`
    private String courseId;
    //   2. `String courseName`
    private String courseName;
    //   3. `double credits`  // e.g.: 1.5, 2, 2.5, 3
    private double credits;
    //   4. `Department department`
    private Department department;
    //   5. `ArrayList<Assignment> assignments`
    private ArrayList<Assignment> assigments;
    //   6. `ArrayList<Student> registeredStudents`
    private ArrayList<Student> registeredStudents;
    private ArrayList<Double> finalScores;
    //   7. `static int nextId` // indicates the next ID that will be used.
    private static int nextId = 0;

    //methods
    //1. `boolean isAssignmentWeightValid()` // checks if the sum of weights of all assignments of that course equals to 100%.

    /*   2. `boolean registerStudent(Student student)` // adds a student to the student list of the course,
     also add a new `null` element to each assignment of this course,
       and add a new `null` element for the `finalScores`.*/

    //   3. `double[] calcStudentsAverage()` // calculates the weighted average score of a student

    //   4. `boolean addAssignment(String assignmentName, double weight, int maxScore)` // adds a new assignment to the course.

    //   5. `void generateScores()` // generates random scores for each assignment and student, and calculates the final score for each student.

    /*   6. `void displayScores()` // displays the scores of a course in a table, with the assignment averages and student weighted average (helper methods might be required).

          ``` data
         example:
          Course: Programming 1(C-D00-01)
                                  Assignment01   Assignment02   Assignment03         Exam01         Exam02    Final Score
                  Ethan Collins             82             82             76             85             80             82
                  Lucas Bennett             97             92             84             67             90             83
                  Ava Harrington            91             68             82             83             83             82
                  Average                   90             81             81             78             84
    */
}
