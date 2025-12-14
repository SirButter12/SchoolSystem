import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Util;

public class UtilTest {

    @Test
    @DisplayName(" IAN    cORanGUEZ   -> Ian Coranguez")
    public void toTitleCaseTestStandard() {
        String expected = "Ian Coranguez";
        String actual = Util.toTitleCase(" IAN    cORanGUEZ  ");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("null -> null")
    public void toTitleCaseTestNull() {
        String expected = null;
        String actual = Util.toTitleCase(null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName(" -> ")
    public void toTitleCaseTestEmptyString() {
        String expected = "";
        String actual = Util.toTitleCase("");
        Assertions.assertEquals(expected, actual);
    }

}
