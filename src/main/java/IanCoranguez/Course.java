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

    /**
     * Creates a new course object and generates a unique course ID in the format C-DepartmentId-courseId.
     *
     * @param courseName the name of the course
     * @param credits the number of credits this course provides
     * @param department the department this course belongs to
     */
    public Course(String courseName, double credits, Department department) {
        this.courseId = String.format("C-%s-%02d", department.getDepartmentId(), nextId++);
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
    }

    /**
     * Checks if the sum of all assignment weights in this course is <= 1.0.
     * Ensures that adding a new assignment does not exceed the maximum total weight.
     *
     * @return true if the sum of all assignments' weights is <= 1.0, false otherwise
     */
    private boolean isAssignmentWeightValid() {
        if (assignments.isEmpty()){
            return true;
        }

        double assWeightSum =0;
        for (Assignment assignment: assignments) {
            assWeightSum += assignment.getWeight();
        }

        return assWeightSum <= 1.0;
    }

    /**
     * Registers a student in this course.
     * - Adds the student to the registeredStudents list.
     * - Adds a placeholder (null) for each existing assignment score.
     * - Adds a placeholder (null) in finalScores.
     * - Adds this course to the student's registeredCourses list.
     *
     * @param student the student to register
     * @return true if the student was successfully registered, false if already enrolled
     */
    public boolean registerStudent(Student student) {
        if (registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);

        for (Assignment assignment : assignments) {
            assignment.addScore(null);
        }

        finalScores.add(null);

        student.getRegisteredCourses().add(this);
        return true;
    }

    /**
     * Removes a student from this course.
     * - Removes the student from registeredStudents.
     * - Removes the student's scores from each assignment.
     * - Removes the student's final score from finalScores.
     * - Removes this course from the student's registeredCourses.
     *
     * @param student the student to remove
     * @return true if the student was successfully removed, false if they were not enrolled
     */
    public boolean removeStudent(Student student) {
        if (!registeredStudents.contains(student)) {
            return false;
        }

        int idx = registeredStudents.indexOf(student);

        for (Assignment assignment : assignments) {
            assignment.removeScore(idx);
        }

        finalScores.remove(idx);

        registeredStudents.remove(idx);

        student.getRegisteredCourses().remove(this);

        return true;
    }

    /**
     * Calculates the weighted average score for all students in this course.
     * - Ignores null scores.
     * - If assignment weights sum < 1.0, the final score represents a partial accumulation.
     * - Updates finalScores list with new averages.
     */
    public void calcStudentsAverage() {
        finalScores.clear();
        int size = registeredStudents.size();

        if (assignments.isEmpty()){
            for (int i = 0; i < size; i++){
                finalScores.add(0.0);
            }
            return;
        }

        for (int i = 0; i < size; i++) {
            double avg = 0;
            for (Assignment assignment: assignments) {
                Integer score = assignment.getScores().get(i);
                if (score == null){
                    continue;
                }
                avg += score * assignment.getWeight();
            }
            finalScores.add(avg);
        }
    }

    /**
     * Represents a course in the academic system.
     * Each course has a unique ID, name, credit value, a department, a list of registered students,
     * a list of assignments, and calculated final scores for each student.
     * Supports registering/dropping students, adding assignments, generating random scores,
     * calculating weighted averages, and displaying scores in a formatted table.
     */
    public boolean addAssignment(String assignmentName, double weight) {
        assignments.add(new Assignment(assignmentName, weight, registeredStudents.size(), this));

        if (!isAssignmentWeightValid()){
            assignments.removeLast();
            return false;
        }

        return true;
    }

    /**
     * Generates random scores for each student in each assignment.
     * Then recalculates all students' final scores.
     */
    public void generateScores(){
        if (!assignments.isEmpty()) {
            for (Assignment assignment : assignments) {
                assignment.fillScoresRandom(registeredStudents.size());
            }
        }

        calcStudentsAverage();
    }

    /** Displays the scores in the following format
     *Course: Programming 1(C-D00-01)
     *                Assignment01   Assignment02   Assignment03         Exam01         Exam02    Final Score
     *Ethan Collins             82             82             76             85             80             82
     *Lucas Bennett             97             92             84             67             90             83
     *Ava Harrington            91             68             82             83             83             82
     *Average                   90             81             81             78             84
     */
    void displayScores(){
        //stores the title cased student names and assigment names in arrrays so they are more easily accessed later
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

        //print the course name and the id
        System.out.printf("Course: %s (%s)\n", courseName, courseId);

        //prints the top row
        System.out.printf("%-14s", "");

        for (String assignmentName: assignmentNames) {
            System.out.printf("%14s ", assignmentName);
        }

        System.out.println("   Final Score");

        //prints each assigment score
        for (int i = 0; i < studentNames.length; i++) {
            System.out.printf("%-14s",studentNames[i]);

            for (int j = 0; j < assignmentNames.length; j++) {
                System.out.printf("%14d ",assignments.get(j).getScores().get(i));
            }

            System.out.printf("%14.0f\n", finalScores.get(i));
        }

        //prints the average for each assigment
        System.out.printf("%-14s", "average");

        for (Assignment assignment: assignments) {
            System.out.printf("%14.0f ", assignment.getAvgScore());
        }

        System.out.printf("\n");
    }

    /**
     * describes the course object in a string
     * @return the object described in a string
     */
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
                assignments += assignment.toString() + "\n";
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

    /**
     * describes the object with a simplified string. This method helps in other classes' toString method
     * @return the simplified string version of the object
     */
    public String toSimplifiedString(){
        return String.format("Course{ name: %s id: %s department: %s}", courseName, courseId, department);
    }

    //this makes sure that registeredStudents, finalScores and assigments can only be modified by the Course class
    ArrayList<Student> getRegisteredStudents(){
        return new ArrayList<>(registeredStudents);
    }

    ArrayList<Double> getFinalScores(){
        return new ArrayList<>(finalScores);
    }

    ArrayList<Assignment> getAssignments(){
        return new ArrayList<>(assignments);
    }
}
