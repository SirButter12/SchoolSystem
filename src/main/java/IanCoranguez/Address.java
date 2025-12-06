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
