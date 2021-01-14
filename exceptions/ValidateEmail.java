package myApp.com.exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateEmail {
    private final static String patternEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean checkEmail(String email) throws EmailException{
        Pattern p = Pattern.compile(patternEmail);
        Matcher matcher = p.matcher(email);
        if(!matcher.matches())
            throw new EmailException("Invalid email!");
        return matcher.matches();
    }
}
