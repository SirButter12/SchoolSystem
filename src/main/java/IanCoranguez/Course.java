package IanCoranguez;

import java.util.ArrayList;

public class Course {
    //fields
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments = new ArrayList<>();
    private ArrayList<Student> registeredStudents = new ArrayList<>();
    private ArrayList<Double> finalScores = new ArrayList<>();
    private static int nextId = 0;

    public Course(String courseName, double credits, Department department) {
        this.courseId = String.format("C-%s-%02d", department.getDepartmentId(), nextId++);
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
    }

    private boolean isAssignmentWeightValid(){
        if (assignments.isEmpty()){
            return false;
        }

        double assWeightSum =0;
        for (Assignment assignment: assignments){
            assWeightSum += assignment.getWeight();
        }

        return !(Math.abs(1.0 - assWeightSum) > 0.000001);
    }

    public boolean registerStudent(Student student) {
        for (Student registeredStudent : registeredStudents) {
            if (student.equals(registeredStudent)) {
                return false;
            }
        }

        registeredStudents.add(student);

        for(int i = 0; i < assignments.size(); i++) {
            assignments.get(i).addScore(null);
        }

        finalScores.add(null);

        return true;
    }


    //   3. `double[] calcStudentsAverage()` // calculates the weighted average score of a student
    public double[] calcStudentsAverage(){
        int size = registeredStudents.size();
        double[] avgs = new double[size];

        if (assignments.isEmpty()){
            return avgs;
        }

        for (int i = 0; i < size; i++){
            for (Assignment assignment: assignments){
                Integer score = assignment.getScores().get(i);
                if (score == null){
                    continue;
                }
                avgs[i] += score * assignment.getWeight();
            }
        }

        return avgs;
    }


    //   4. `boolean addAssignment(String assignmentName, double weight)` // adds a new assignment to the course.
    public boolean addAssignment(String assgmentName, double weight){
        assignments.add(new Assignment(assgmentName, weight, registeredStudents.size()));

        if (!isAssignmentWeightValid()){
            assignments.remove(assignments.size() - 1);
            return false;
        }

        return true;
    }

    //   5. `void generateScores()` // generates random scores for each assignment and student, and calculates the final score for each student.
    public void generateScores(){
        if (!assignments.isEmpty()){
            for (int i = 0; i < assignments.size(); i++){
                assignments.get(i).fillScoresRandom(registeredStudents.size());
            }
        }

        double[] avgs = calcStudentsAverage();
        finalScores.clear();
        for (double avg : avgs) {
            finalScores.add(avg);
        }
    }


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
