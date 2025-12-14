#SCHOOL SYSTEM FINAL PROJECT FOR INTRODICTION TO PROGRAMMING

##Author: Ian Obed Coranguez Esquivel 2556538

This is the final project for introduction to programming 
This project mimics an school system that allows the students to register or drop from a course and allows the teacher to display the scores of a course.

Some details about Each class:
So basicalliy I've implemented what I was told to, but with some modifications that make this project more personal and, to my perspective, better.
- Address and department classes: Address and department classes are the same as the instruction version.

- Student class: when student class calls dropCourse() or registerCourse(), it basically just calls the registerStudent(student)/removeStudent(student) with itself as parameter, this allows me to make changes just in one place.

- Assignment class: 
  - Now each assignment need a reference to its father course, this is just to ensure that the StudentAverages in the course and the actual weight of each assignment stays sync.
  - I splitted generateRandomScores() method in two functions to be able to generate individual scores if needed
  - addScore and removeScore methods, this is just to support getScores which gives a copy of the actual scores to avoid messing up the average accidentally
  - setWeight, this changes the weight of the assignment and it also makes the father Course recalculate the final scores.

-Course class:
  - isAssignmentWeightValid will chack if the sum of all assignments is less or equal to 1.0 rather to equal to 1.0, bacause I decided to reuse this method for the add assignment method. So if it is valid only if sum == 1.0 then the addAssignment method will only work with assignment weights of 1.0
  - register/removeStudent(student), this now does all the job of removing/registering an student from/to a course, removing/adding itself from student's courses and adding/removing the student to/from  the registeredStudents list.
  - calcStudentsAvg() now calculates the final scores diretly since it is the only thing it is used and will be used for so now it returns void rather than a double[]
  - generateScores will also calculare the final score of each student
  - all the get methods that should get a method now gets a deep copy rather than a shallow copy