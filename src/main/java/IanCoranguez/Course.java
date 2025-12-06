package IanCoranguez;

import lombok.Getter;
import lombok.Setter;
import util.Util;

import java.util.ArrayList;

public class Course {
    //fields
    @Getter
    private String courseId;
    @Getter @Setter
    private String courseName;
    @Getter @Setter
    private double credits;
    @Getter @Setter
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

    private boolean isAssignmentWeightValid() {
        if (assignments.isEmpty()){
            return true;
        }

        double assWeightSum =0;
        for (Assignment assignment: assignments) {
            assWeightSum += assignment.getWeight();
        }

        return assWeightSum < 1.0;
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);

        for(int i = 0; i < assignments.size(); i++) {
            assignments.get(i).addScore(null);
        }

        finalScores.add(null);

        student.getRegisteredCourses().add(this);
        return true;
    }

    public boolean removeStudent(Student student) {
        if (!registeredStudents.contains(student)) {
            return false;
        }
        int idx = registeredStudents.indexOf(student);

        registeredStudents.remove(idx);

        for (int i = 0; i < assignments.size(); i++) {
            assignments.get(i).removeScore(idx);
        }
        return true;
    }

    //   3. `double[] calcStudentsAverage()` // calculates the weighted average score of a student
    public double[] calcStudentsAverage() {
        int size = registeredStudents.size();
        double[] avgs = new double[size];

        if (assignments.isEmpty()){
            return avgs;
        }

        for (int i = 0; i < size; i++) {
            for (Assignment assignment: assignments) {
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
    public boolean addAssignment(String assgmentName, double weight) {
        assignments.add(new Assignment(assgmentName, weight, registeredStudents.size()));

        if (!isAssignmentWeightValid()){
            assignments.removeLast();
            return false;
        }

        return true;
    }

    //   5. `void generateScores()` // generates random scores for each assignment and student, and calculates the final score for each student.
    public void generateScores(){
        if (!assignments.isEmpty()) {
            for (int i = 0; i < assignments.size(); i++) {
                assignments.get(i).fillScoresRandom(registeredStudents.size());
            }
        }

        double[] avgs = calcStudentsAverage();
        finalScores.clear();
        for (double avg : avgs) {
            finalScores.add(avg);
        }
    }

    public String toSimplifiedString(){
        return String.format("Course{ name: %s id: %s department: %s}", courseName, courseId, department);
    }

    public String toString(){
        String students = "";
        String assignments = "";
        if (!registeredStudents.isEmpty()){
            for (Student registeredStudent: registeredStudents){
                students += registeredStudent.toSimplifiedString() + "\n";
            }
        }
        if (!this.assignments.isEmpty()){
            for (Assignment assignment: this.assignments){
                assignments += assignment.toSimplifiedString() + "\n";
            }
        }

        return String.format("Course { \n" +
                "id = %s \n" +
                "name = %s \n" +
                "credits = %s \n" +
                "department = %s \n" +
                "registered students: \n" +
                "%s \n" +
                "Assignments: \n" +
                "%s" +
                "}", courseId, courseName, credits, department, students, assignments);
    }

    ArrayList<Student> getRegisteredStudents(){
        return new ArrayList<>(registeredStudents);
    }

    ArrayList<Double> getFinalScores(){
        return new ArrayList<>(finalScores);
    }

    ArrayList<Assignment> getAssignments(){
        return new ArrayList<>(assignments);
    }

    void displayScores(){
        int amoStudents = registeredStudents.size();
        int amoAssignments = assignments.size();

        String[] studentNames = new String[amoStudents];
        String[] assignmentNames = new String[amoAssignments];

        for (int i = 0; i < amoStudents; i++){
            studentNames[i] = Util.toTitleCase(registeredStudents.get(i).getStudentName());
        }

        for (int i = 0; i < amoAssignments; i++){
            assignmentNames[i] = Util.toTitleCase(assignments.get(i).getAssignmentName());
        }

        System.out.printf("%-14s", "");
        for (String assignmentName: assignmentNames) {
            System.out.printf("%14s ", assignmentName);
        }
        System.out.println("   Final Score");

        for (int i = 0; i < studentNames.length; i++){
            System.out.printf("%-14s",studentNames[i]);
            for (int j = 0; j < assignmentNames.length; j++) {
                System.out.printf("%14d",assignments.get(j).getScores().get(i));
            }
            System.out.printf("%14.0f\n", finalScores.get(i));
        }

        System.out.printf("%-14s", "average");
        for (Assignment assignment: assignments){
            System.out.printf("%14s", assignment.getAvgScore());
        }
    }
}
