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

    public Department(String departmentName) {
        if (!Department.isDepartmentNameValid(departmentName)) {
            this.departmentName = null;
            this.departmentId = null;
        } else {
            this.departmentName = departmentName;
            this.departmentId = String.format("D%02d", nextId++);
        }
    }

    public void setDepartmentName(String departmentName) {
        if (Department.isDepartmentNameValid(departmentName)){
            this.departmentName = departmentName;
        }
    }

}