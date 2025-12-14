import IanCoranguez.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DepartmentTest {

    @Test
    @DisplayName("Mathematics -> true")
    public void isDepartmentNameValidTrueStandardCase(){
        boolean expected = true;
        boolean actual = Department.isDepartmentNameValid("Mathematics");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1 -> false")
    public void isDepartmentNameValidFalseStandardCase(){
        boolean expected = false;
        boolean actual = Department.isDepartmentNameValid("1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("null -> false")
    public void isDepartmentNameNullCase(){
        boolean expected = false;
        boolean actual = Department.isDepartmentNameValid(null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName(" -> false")
    public void isDepartmentNameValidEmptyStrCase(){
        boolean expected = false;
        boolean actual = Department.isDepartmentNameValid("");
        Assertions.assertEquals(expected, actual);
    }



}
