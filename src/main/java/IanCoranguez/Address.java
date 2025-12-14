package IanCoranguez;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
/**
 * Address class describing an address, it has only valid postal codes under de format LNLNLN (L = letter, N = digit)
 */
public class Address {
    //fields
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;

    /**
     * Creates a new Address object.
     * - Initializes the street number, street name, city, province, and postal code.
     * - Validates the postal code using the {@link #isPostalCodeValid(String)} method.
     * - If the postal code is invalid, streetNo is set to -1 and the remaining fields are set to null.
     *
     * @param streetNo   the street number of the address
     * @param street     the street name
     * @param city       the city of the address
     * @param province   the province of the address, using the {@link Province} enum
     * @param postalCode the postal code in the format LNLNLN (L = letter, N = digit)
     */
    public Address(int streetNo, String street, String city, Province province, String postalCode) {
        if (Address.isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase();
        } else {
            this.streetNo = -1;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
        }

    }

    /**
     * Validates a Canadian postal code.
     * - Checks that the postal code is exactly 6 characters long.
     * - The expected format is LNLNLN, where L is a letter and N is a digit.
     *
     * @param postalCode the postal code to validate
     * @return true if the postal code is valid, false otherwise
     */

    public static boolean isPostalCodeValid(String postalCode) {
        if (postalCode == null || postalCode.length() != 6) {
            return false;
        }

        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0){
                if (Character.isLetter(postalCode.charAt(i))) {
                    continue;
                }
                return false;
            }

            if (Character.isDigit(postalCode.charAt(i))) {
                continue;
            }
            return false;
        }

        return true;
    }

    /**
     * Enumerates all Canadian provinces with their official abbreviations.
     */
    public enum Province {
        AB, BC, MB, NL, NS, ON, QC, YT, NB, NT, NU, PE, SK
    }
}
