package IanCoranguez;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

@EqualsAndHashCode
public class Assignment {
    //fields
    @Getter
    private final String assignmentId;
    @Getter @Setter
    private String assignmentName;
    @Getter @Setter
    private double weight;
    @Getter
    private double avgScore;
    private ArrayList<Integer> scores = new ArrayList<>();
    private static int nextId = 0;
    private static final Random rand = new Random();

    //methods

    //1. `void calcAssignmentAvg()`    // calculates the average score for the assignment.

    public Assignment(String assigmentName, double weight, int amountStudInCourse) {
        this.assignmentName = assigmentName;
        this.weight = weight;
        this.assignmentId = String.format("%04d", nextId++);
        fillScoresRandom(amountStudInCourse);
        calcAssignmentAvg();
    }

    /*2. `void generateRandomScore()`  // generates random scores for all students in an assignment, the scores are generated with the following rule:
     Firstly generate a random number in range `[0, 10]`, then
          - if the number is `0`, then generate a random score in range `[0, 60)` for the student;
          - if the number is `1`, `2`, then generate a random score in range `[60, 70)` for the student;
          - if the number is `3`, `4`, then generate a random score in range `[70, 80)` for the student;
          - if the number is `5`, `6`, `7`, `8`, then generate a random score in range `[80, 90)` for the student;
          - if the number is `9`, `10`, then generate a random score in range `[90, 100]` for the student;
     */

    //this allows me to reuse generateRandomScore if I need it
    public void fillScoresRandom(int amountScoresNeeded) {
        for (int i = 0; i < amountScoresNeeded; i++) {
            scores.add(generateRandomScore());
        }
    }

    public int generateRandomScore() {
        return switch (rand.nextInt(11)) {
            case 0 -> rand.nextInt(0,60);
            case 1, 2 -> rand.nextInt(60,70);
            case 3, 4 -> rand.nextInt(70, 80);
            case 5, 6, 7, 8 -> rand.nextInt(80, 90);
            default -> rand.nextInt(90, 101);
        };
    }

    private void calcAssignmentAvg() {
        avgScore = 0;

        if (scores.size() == 0) {
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

    public void addScore(Integer score) {
        scores.add(score);
        calcAssignmentAvg();
    }

    //this just makes sure that the average stays relevant and eliminates the risk of accidentally messing it up
    public ArrayList<Integer> getScores() {
        return new ArrayList<>(scores);
    }


    //   3. `toString` // generates a string to represent an assignment, with `assignmentId`, `assignmentName`, and `weight`.

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
