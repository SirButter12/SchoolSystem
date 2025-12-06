package IanCoranguez;

public class Main {
    public static void main(String[] args) {
        // --- Crear departamentos ---
        Department csDept = new Department("Computer Science");
        Department mathDept = new Department("Mathematics");

        // --- Crear cursos ---
        Course prog1 = new Course("Programming 1", 3.0, csDept);
        Course math1 = new Course("Calculus 1", 4.0, mathDept);

        // --- Crear direcciones ---
        Address addrAlice = new Address(123, "Main St", "Montreal", Address.Province.QC, "H1A1A1");
        Address addrBob = new Address(456, "Oak Ave", "Montreal", Address.Province.QC, "H2B2B2");
        Address addrCarol = new Address(789, "Pine Rd", "Montreal", Address.Province.QC, "H3C3C3");

        // --- Crear estudiantes ---
        Student alice = new Student("Alice Johnson", Student.Gender.FEMALE, addrAlice, csDept);
        Student bob = new Student("Bob Smith", Student.Gender.MALE, addrBob, mathDept);
        Student carol = new Student("Carol Lee", Student.Gender.FEMALE, addrCarol, csDept);

        // --- Registrar estudiantes en cursos ---
        prog1.registerStudent(alice);
        prog1.registerStudent(bob);
        prog1.registerStudent(carol);

        math1.registerStudent(bob);
        math1.registerStudent(alice);

        // --- Agregar assignments ---
        prog1.addAssignment("Assignment01", 0.2);
        prog1.addAssignment("Assignment02", 0.3);
        prog1.addAssignment("Exam01", 0.5); // total 1.0

        math1.addAssignment("Homework01", 0.4);
        math1.addAssignment("Midterm", 0.6);

        // --- Generar scores aleatorios ---
        prog1.generateScores();
        math1.generateScores();

        // --- Mostrar resultados ---
        System.out.println("=== Programming 1 Scores ===");
        prog1.displayScores();

        System.out.println("\n=== Calculus 1 Scores ===");
        math1.displayScores();

        // --- Test: remover estudiante ---
        System.out.println("\n--- Removing Bob from Programming 1 ---");
        prog1.removeStudent(bob);
        prog1.displayScores();

        // --- Test: cambiar peso de assignment ---
        System.out.println("\n--- Changing weight of Assignment01 to 0.25 ---");
        prog1.getAssignments().get(0).setWeight(0.25);
        prog1.displayScores();

        // --- Test: agregar assignment que exceda el peso ---
        System.out.println("\n--- Attempting to add Assignment03 with weight 0.6 (should fail) ---");
        boolean added = prog1.addAssignment("Assignment03", 0.6);
        System.out.println("Added? " + added);
        prog1.displayScores();

        Course french = new Course("French", 2, new Department("Francais"));

        french.generateScores();
        french.displayScores();

        System.out.println(alice);
        System.out.println(prog1);
    }
}

