package IanCoranguez;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an assignment in a course.
 * Each assignment has a unique ID, name, weight, a list of student scores,
 * and a reference to the course it belongs to.
 * It supports random score generation and calculation of the assignment's average score.
 */

@EqualsAndHashCode
public class Assignment {
    //fields
    @Getter
    private final String assignmentId;
    @Getter @Setter
    private String assignmentName;
    @Getter
    private double weight;
    @Getter
    private double avgScore;
    private ArrayList<Integer> scores = new ArrayList<>();
    private static int nextId = 0;
    private static final Random rand = new Random();
    @Getter
    private Course fatherCourse;

    /**
     * Creates a new Assignment object.
     * - Generates a unique ID for the assignment.
     * - Sets the assignment name and weight (0.0 <= weight <= 1.0).
     * - Fills the scores list with default random scores for all students in the course.
     * - Calculates the initial average score.
     *
     * @param assignmentName the name of the assignment (e.g., "Assignment01")
     * @param weight the weight of the assignment (clamped to [0.0, 1.0])
     * @param amountStudInCourse number of students in the course (used to initialize scores)
     * @param fatherCourse the course to which this assignment belongs
     */
    public Assignment(String assignmentName, double weight, int amountStudInCourse, Course fatherCourse) {
        this.assignmentName = assignmentName;
        this.weight = Math.max(Math.min(1.0, weight), 0.0);
        this.assignmentId = String.format("%04d", nextId++);
        this.fatherCourse = fatherCourse;
        fillScoresRandom(amountStudInCourse);
        calcAssignmentAvg();
    }



    /**
     * Fills the scores list with randomly generated scores.
     * - Clears any existing scores.
     *
     * @param amountScoresNeeded the number of scores to generate
     */
    public void fillScoresRandom(int amountScoresNeeded) {
        scores.clear();
        for (int i = 0; i < amountScoresNeeded; i++) {
            scores.add(generateRandomScore());
        }
    }
    /*
    I splited these two functions to be able to re-use generateRandom score if needed, for example if we want to implement a new method that sets
    an student score individually, this will be save some time since we wouldn't have to write this again, plus we can change the general logic of
    a random score generation in just one place.
    */

    /**
     * Generates a semi-random score in the range 0-100.
     * - Skews distribution to produce higher scores more often.
     *
     * @return a random integer score between 0 and 100
     */
    public int generateRandomScore() {
        return switch (rand.nextInt(11)) {
            case 0 -> rand.nextInt(0,60);
            case 1, 2 -> rand.nextInt(60,70);
            case 3, 4 -> rand.nextInt(70, 80);
            case 5, 6, 7, 8 -> rand.nextInt(80, 90);
            default -> rand.nextInt(90, 101);
        };
    }

    /**
     * Calculates the average of all scores in this assignment.
     * - Ignores null scores.
     * - Updates the avgScore field.
     */
    private void calcAssignmentAvg() {
        avgScore = 0;

        if (scores.isEmpty()) {
            return;
        }

        for (Integer score : scores) {
            if (score == null) {
                continue;
            }
            avgScore += score;
        }

        avgScore /= scores.size();
    }

    /**
     * Adds a new score to this assignment and recalculates the average.
     *
     * @param score the score to add (can be null)
     */
    public void addScore(Integer score) {
        scores.add(score);
        calcAssignmentAvg();
    }

    /**
     * Removes the score at a given index and recalculates the average.
     * - Does nothing if the index is invalid.
     *
     * @param idx the index of the score to remove
     */
    public void removeScore(int idx) {
        if (idx > scores.size() - 1 || idx < 0){
            return;
        }

        scores.remove(idx);
        calcAssignmentAvg();

    }

    /**
     * Returns a string representation of this assignment.
     * Shows assignment name, ID, and weight.
     *
     * @return detailed string representation
     */
    public String toString() {
        return String.format("Assignment{ name = %s id = %s weight = %s}", assignmentName, assignmentId, weight);
    }


    /**
     * Returns a copy of the scores list to prevent external modification.
     *
     * @return a new ArrayList containing all scores
     */
    public ArrayList<Integer> getScores() {
        return new ArrayList<>(scores);
    }

    /**
     * Sets a new weight for the assignment.
     * - Clamps the weight to [0.0, 1.0].
     * - Updates course averages automatically.
     *
     * @param weight the new weight of the assignment
     */
    public void setWeight(double weight){
        this.weight = Math.max(Math.min(1.0, weight), 0.0);
        fatherCourse.calcStudentsAverage();
    }
}
