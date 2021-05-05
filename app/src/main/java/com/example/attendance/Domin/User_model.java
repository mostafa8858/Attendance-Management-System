package com.example.attendance.Domin;

public class User_model {
    String firstName,secondName,mobile,emailSinUp,password,re_password;

    public User_model() {
    }

    public User_model(String firstName, String secondName, String mobile, String emailSinUp, String password, String re_password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.mobile = mobile;
        this.emailSinUp = emailSinUp;
        this.password = password;
        this.re_password = re_password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailSinUp() {
        return emailSinUp;
    }

    public void setEmailSinUp(String emailSinUp) {
        this.emailSinUp = emailSinUp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRe_password() {
        return re_password;
    }

    public void setRe_password(String re_password) {
        this.re_password = re_password;
    }
}
