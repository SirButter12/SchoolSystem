package IanCoranguez;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
class Department {
    //fields
    //1. `String departmentId`   // 2-digits starts with a character `D`. This id should be increased automatically.
    private String departmentId;
    //   2. `String departmentNam
    @Setter
    private String departmentName;
    //   3. `static int nextId`     // indicates the next ID that will be used
    private static int nextId = 0;

    //Methods
    //1. `static boolean isDepartmentNameValid(String departmentName)` // checks if a department name is valid or not, a department name should only contain letters or space
    public static boolean isDepartmentNameValid(String departmentName){
        char[] nameCharArray = departmentName.toCharArray();

        for (char character: nameCharArray){
            if (!(Character.isAlphabetic(character) && Character.isSpaceChar(character))) {
                return false;
            }
        }
        return true;
    }

    //   2. Constructor with only `departmentName` // if the `departmentName` is invalid, create the object with everything as `null`;

    public Department(String departmentName) {
        if (!Department.isDepartmentNameValid(departmentName)){
            this.departmentName = null;
            this.departmentId = null;
        } else {
            this.departmentName = departmentName;
            this.departmentId = String.format("D%04i", nextId++);
        }
    }
}