package IanCoranguez;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class Department {
    //fields
    private String departmentId;
    private String departmentName;
    private static int nextId = 0;

    //Methods
    /**
     * creates a new department name, it first checks if the department name is valid, if it isn't it will set all department fields to null
     * @param departmentName the department name
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
     * changes the department name
     * if the name is not valid the name doesn't get changed
     * @param departmentName the new department name
     */
    public void setDepartmentName(String departmentName) {
        if (Department.isDepartmentNameValid(departmentName)){
            this.departmentName = departmentName;
        }
    }

    /**
     * Checks if the department name is valid by checking if it just contains letters and spaces
     * @param departmentName the department name
     * @return true if it is valid, false otherwise
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