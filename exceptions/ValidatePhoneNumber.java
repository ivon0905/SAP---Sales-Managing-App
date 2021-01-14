package myApp.com.exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatePhoneNumber {
    private final static String patternPhone = "^[0]{1}[7-9]{2}[0-9]{7}";

    public static boolean checkPhoneNum(String phone) throws PhoneNumberException {
        Pattern p = Pattern.compile(patternPhone);
        Matcher m = p.matcher(phone);
        if(!m.matches())
            throw new PhoneNumberException("Invalid phone number!");
        return m.matches();
    }
}
