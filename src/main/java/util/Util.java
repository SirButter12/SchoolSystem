package util;


public class Util {
    public static String toTitleCase(String str){
        str = str.trim();
        str = str.toLowerCase();

        String[] words = str.split(" ");
        str = "";

        for (String word: words){
            if (word.isEmpty()){
                continue;
            }

            word = word.substring(0, 1).toUpperCase() + word.substring(1) + " ";
            str += word;
        }

        str = str.trim();

        return str;

    }
}
