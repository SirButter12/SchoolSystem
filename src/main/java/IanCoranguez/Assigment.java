package IanCoranguez;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

@Getter
@Setter
public class Assigment {
    //fields
    //   1. `String assignmentId`   // This id should be increased automatically.
    private String assigmentId;
    //   2. `String assignmentName`
    private String AssigmentName;
    //   3. `double weight`         // the sum of weights of all assignment should be 100
    private double weight;
    //   4. `ArrayList<Integer> scores`
    private ArrayList<Integer> scores;
    //   5. `static int nextId`     // indicates the next ID that will be used
    private static int nextId;
    private double avgScore = 0;

    //methods
    public Assigment(String assigmentName, double weight) {
        AssigmentName = assigmentName;
        this.weight = weight;
        generateRandomScore();
        calcAssignmentAvg();
    }

    //1. `void calcAssignmentAvg()`    // calculates the average score for the assignment.
    private void calcAssignmentAvg(){
        for (int score: scores){
            avgScore += score;
        }
        avgScore /= scores.size();
    }

    /*2. `void generateRandomScore()`  // generates random scores for all students in an assignment, the scores are generated with the following rule:
     Firstly generate a random number in range `[0, 10]`, then
          - if the number is `0`, then generate a random score in range `[0, 60)` for the student;
          - if the number is `1`, `2`, then generate a random score in range `[60, 70)` for the student;
          - if the number is `3`, `4`, then generate a random score in range `[70, 80)` for the student;
          - if the number is `5`, `6`, `7`, `8`, then generate a random score in range `[80, 90)` for the student;
          - if the number is `9`, `10`, then generate a random score in range `[90, 100]` for the student;
     */
    private void generateRandomScore(){
        Random rand = new Random();
        int num = rand.nextInt(0,11);
        for (int score: scores){
            score = switch (num) {
                case 0 -> rand.nextInt(0, 60);
                case 1, 2 -> rand.nextInt(60, 70);
                case 3, 4 -> rand.nextInt(70, 80);
                case 5, 6, 7, 8 -> rand.nextInt(80, 90);
                default -> rand.nextInt(90, 101);
            };
        }
    }

    public void addScore(){
        scores.add(null);
    }


    //   3. `toString` // generates a string to represent an assignment, with `assignmentId`, `assignmentName`, and `weight`.

    @Override
    public String toString() {
        return "Assigment{" +
                ", AssigmentName = '" + AssigmentName + '\'' +
                ", assigmentId = '" + assigmentId + '\'' +
                "weight = " + weight +
                '}';
    }
}
