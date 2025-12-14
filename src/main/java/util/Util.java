package util;

/**
 * Utility class containing common helper methods.
 */
public class Util {
    /**
     * Converts a given string to Title Case.
     * <p>
     * Each word's first character is converted to uppercase and the remaining characters
     * are converted to lowercase. Extra spaces are trimmed.
     * </p>
     *
     * @param str the input string to be converted
     * @return the string in Title Case, or an empty string if input is null or empty
     */
    public static String toTitleCase(String str) {
        if (str == null) {
            return str;
        }

        str = str.trim();
        str = str.toLowerCase();

        String[] words = str.split(" ");
        str = "";

        for (String word: words) {
            if (word.isEmpty()) {
                continue;
            }

            word = word.substring(0, 1).toUpperCase() + word.substring(1) + " ";
            str += word;
        }

        str = str.trim();

        return str;

    }
}
