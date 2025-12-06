package IanCoranguez;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Address {
    //fields
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;

    /**
     * creates a new address object filling its fields with the constructor's parameter, it also checks if the postal code is valid
     * if it is not valid, the street number will be set to -1 and the rest of the fields will be set to null
     * @param streetNo the street number of the address
     * @param street the street name of the address
     * @param city the city where the address is located
     * @param province the province
     * @param postalCode the postal code
     */
    public Address(int streetNo, String street, String city, Province province, String postalCode) {
        if (Address.isPostalCodeValid(postalCode)){
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
     * checks if the postal code is valid. by checking if it follows the following 6-digit format: LNLNLN where N is a number
     * an L is a letter
     * @param postalCode the postal code to be validated
     * @return true if it's valid, false otherwise
     */
    public static boolean isPostalCodeValid(String postalCode){
        if (postalCode == null || postalCode.length() != 6){
            return false;
        }

        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0){
                if (Character.isLetter(postalCode.charAt(i))){
                    continue;
                }
                return false;
            }

            if (Character.isDigit(postalCode.charAt(i))){
                continue;
            }
            return false;
        }

        return true;
    }

    /**
     *Enumerates all canadian provinces with their official abreviation.
     */
    public enum Province {
        AB, BC, MB, NL, NS, ON, QC, YT, NB, NT, NU, PE, SK
    }
}
