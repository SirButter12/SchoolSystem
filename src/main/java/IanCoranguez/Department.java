package IanCoranguez;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents an academic department in the institution.
 * Each department has a unique ID and a name.
 * The class ensures that department names are valid (letters and spaces only)
 * and provides a way to safely change the department's name.
 */
@ToString
@EqualsAndHashCode
@Getter
public class Department {
    private String departmentId;
    private String departmentName;
    private static int nextId = 0;

    /**
     * Creates a new Department object.
     * - Generates a unique ID for the department in the format "DXX", where XX is a zero-padded number.
     * - Validates the department name: only alphabetic characters and spaces are allowed.
     * - If the name is invalid, both departmentId and departmentName are set to null.
     *
     * @param departmentName the name of the department
     */
    public Department(String departmentName) {
        if (!Department.isDepartmentNameValid(departmentName)) {
            this.departmentName = null;
            this.departmentId = null;
        } else {
            this.departmentName = departmentName;
            this.departmentId = String.format("D%02d", nextId++);
        }
    }

    /**
     * Updates the department name.
     * - The new name is only applied if it is valid (letters and spaces only).
     * - If the name is invalid, the departmentName remains unchanged.
     *
     * @param departmentName the new department name
     */
    public void setDepartmentName(String departmentName) {
        if (Department.isDepartmentNameValid(departmentName)){
            this.departmentName = departmentName;
        }
    }

    /**
     * Validates a department name.
     * - Only alphabetic characters and spaces are considered valid.
     * - Null or empty strings are considered invalid.
     *
     * @param departmentName the department name to validate
     * @return true if the name is valid, false otherwise
     */
    public static boolean isDepartmentNameValid(String departmentName) {
        if (departmentName == null || departmentName.isEmpty()) {
            return false;
        }

        for (char character: departmentName.toCharArray()) {
            if (!(Character.isAlphabetic(character) || Character.isSpaceChar(character))) {
                return false;
            }
        }
        return true;
    }

}