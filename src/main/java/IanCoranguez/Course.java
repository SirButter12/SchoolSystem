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
     *Creates a new course object with the parameters specified, it also initializes an ID under the following format
     *C-DepartmentId-courseId
     * @param courseName the course name
     * @param credits the credits this course should have
     * @param department the department of this course
     */
    public Course(String courseName, double credits, Department department) {
        this.courseId = String.format("C-%s-%02d", department.getDepartmentId(), nextId++);
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
    }

    /**
     * This will check if the sum of all assigment weights is less or equal 1.0, simply because assigmets will be added one by one
     * and an assigment weight sum != 1.0 will only cause that no assigment will be registered if its weight is not exactly one
     * @return returns true if the sum of all assigments weights is less or equal to one false otherwise
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
     * Will first check if student is already enrolled in the class, if it is the case, returns false
     * otherwise it will first add it to the registeredStudents list, then for each already existent assigment it will
     * add a new null element, then it will add a nell element to the final scores, and at the end it will add
     * this course object to the students registeredCourses list
     * @param student the student to be added
     * @return returns true if the student was enrolled succesfully false if they were already enrolled in the course
     */
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

    /**
     * Will first check if student is not enrolled in the class, if it is the case, returns false
     * otherwise it will first remove it from the registeredStudents list, then for each already existent assigment it will
     * remove its corresponding element, then it will remove its corresponding element from the final scores, and at the end it will remove
     * this course object to the students registeredCourses list
     * @param student the student to be removed from the course
     * @return returns true if the student was removed succesfully false if it wasn't in the course in the first place
     */
    public boolean removeStudent(Student student) {
        if (!registeredStudents.contains(student)) {
            return false;
        }
        int idx = registeredStudents.indexOf(student);

        registeredStudents.remove(idx);

        student.getRegisteredCourses().remove(this);

        for (int i = 0; i < assignments.size(); i++) {
            assignments.get(i).removeScore(idx);
        }
        return true;
    }

    /**
     * calculates the weighted average of a student. This average will be incomplete unless all assigments weights sum 1.0
     * which means that this is more like how much of the final score has been accumulated
     * @return the weighted average of a student
     */
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

    /**
     * This will create a new assigment, it will first check if the sum of all assigments (including the new one) is less or equal to one
     * if so the assigment will be created and added to the assigment list
     * @param assignmentName The assignment name for example: Assignmen01
     * @param weight the weight of the assigment (IMPORTANT: This should go from 0.0 to 1.0 for the assigment to be valid)
     * @return returns true if the assigment was succesfully added. false if it was not a valid assignment
     */
    public boolean addAssignment(String assignmentName, double weight) {
        assignments.add(new Assignment(assignmentName, weight, registeredStudents.size()));

        if (!isAssignmentWeightValid()){
            assignments.removeLast();
            return false;
        }

        return true;
    }

    /**
     * This generates random scores for each student assignment and calculates the final score of each student
     */
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

        //Makes sure there's final scores to print
        generateScores();

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
            System.out.printf("%14.0s ", assignment.getAvgScore());
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
