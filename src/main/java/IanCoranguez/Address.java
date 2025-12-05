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
    //   1. `int streetNo`
    private int streetNo;
    //   2. `String street`
    private String street;
    //   3. `String city`
    private String city;
    //   4. `Province province`  // enum, contains abbriviations of all provinces in Canada
    private Province province;
    //   5. `String postalCode`  // len of 6
    private String postalCode;

    //Methods
    /*1. helper method `static boolean isPostalCodeValid(String postalCode)` to checks if a postcode is valid or not. The length of a postal code can only be `6`.
          1. the postcode must follow the format: `CDCDCD`, where `C` is a character, while `D` is a digit, such as `A1B2C3`.
          2. Note: this method is a static method, which means it requires a parameter of `postalCode` instead of using the field `postalCode`,
     the reason of this is because this method should be called in the constructor and setter, before assigning the input `postalCode` to the field `postalCode`,
      only valid `postalCode` will be assigned to fields.
     */
    public static boolean isPostalCodeValid(String postalCode){
        if (postalCode == null || postalCode.isEmpty()){
            return false;
        }

        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0){
                if (Character.isAlphabetic(postalCode.charAt(i))){
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

    //   2. All argument Constructor, in which the method `isPostalCodeValid()` will first be called to check if the parameter `postalCode` is valid or not,
    //   if it is valid, then set all fields, while postal code with all character uppercase to the field, else set everything as `null`.

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

    public enum Province {
        AB, BC, MB, NL, NS, ON, QC, YT, NB, NT, NU, PE, SK
    }
}
