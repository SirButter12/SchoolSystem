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
    public Assignment(String assignmentName, double weight, int amountStudInCourse) {
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.assignmentId = String.format("%04d", nextId++);
        fillScoresRandom(amountStudInCourse);
        calcAssignmentAvg();
    }

    //this allows me to reuse generateRandomScore if I need it
    public void fillScoresRandom(int amountScoresNeeded) {
        scores.clear();
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

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
