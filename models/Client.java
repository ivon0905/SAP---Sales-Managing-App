package myApp.com.models;

public class Client {
    private String name;
    private String lastName;
    private String email;
    private String phoneNum;

    public Client(String name, String lastName, String email, String phoneNum){
        this.name = name;
        this.lastName=lastName;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
}
