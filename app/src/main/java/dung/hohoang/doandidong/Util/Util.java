package dung.hohoang.doandidong.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static final String URL_REGEX = "((http|https)://)(www.)?"
            + "[a-zA-Z0-9@:%._\\+~#?&//=]"
            + "{2,256}\\.[a-z]"
            + "{2,6}\\b([-a-zA-Z0-9@:%"
            + "._\\+~#?&//=]*)";

    public static boolean validateURL(String dataString){
        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(dataString);

        return matcher.find();
    }
}
