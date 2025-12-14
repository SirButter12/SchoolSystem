import IanCoranguez.Address;
import IanCoranguez.Assignment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    @DisplayName("A1A1A1 -> true")
    public void isPostalCodeValidStandardTrueTest() {
        boolean expected = true;
        boolean actual = Address.isPostalCodeValid("A1A1A1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("111111 -> false")
    public void isPostalCodeValidOnlyNumbersTest() {
        boolean expected = false;
        boolean actual = Address.isPostalCodeValid("111111");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("AAAAAA -> false")
    public void isPostalCodeValidOnlyLettersTest() {
        boolean expected = false;
        boolean actual = Address.isPostalCodeValid("AAAAAA");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("A2A2A2A -> false")
    public void isPostalCodeTooLongTest() {
        boolean expected = false;
        boolean actual = Address.isPostalCodeValid("A2A2A2A");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("A2A2A -> false")
    public void isPostalCodeToShortTest() {
        boolean expected = false;
        boolean actual = Address.isPostalCodeValid("A2A2A");
        Assertions.assertEquals(expected, actual);
    }

}
