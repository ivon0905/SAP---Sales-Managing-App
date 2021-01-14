package myApp.com.exceptions;

public class PhoneNumberException extends Throwable {

    public PhoneNumberException(String s) {
        System.out.println(s);
    }

    public String getMessage(){
        return "Invalid phone number!";
    }
}
