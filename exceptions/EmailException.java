package myApp.com.exceptions;

public class EmailException extends Throwable {

    public EmailException(String s) {
        System.out.println(s);
    }

    public String getMessage(){
       return "Invalid email";
   }
}
