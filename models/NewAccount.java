package myApp.com.models;

public class NewAccount {
    private String name;
    private String lName;
    private String email;
    private String phone;
    private String brand;
    private String user;
    private String pass;

    public NewAccount(String user, String pass){
        this.user = user;
        this.pass = pass;
    }

    public NewAccount(String name, String lName, String email, String phone, String brand){
        this.name = name;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public String getLName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBrand() {
        return brand;
    }

    public String getUser(){ return user; }

    public String getPass(){
        return pass;
    }
}
