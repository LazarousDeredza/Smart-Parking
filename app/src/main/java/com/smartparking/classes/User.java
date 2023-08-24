package com.smartparking.classes;

import java.io.Serializable;

public class User implements Serializable {

    public int userType,isVerified;
    public String name,email,contact_no;

    public User(){}

    public User(String name,String email, String contact_no, int userType,int isVerified){
        this.name=name;
        this.contact_no=contact_no;
        this.email=email;
        this.userType=userType;
        this.isVerified=isVerified;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    @Override
    public String toString() {
        return "User{" +
                "userType=" + userType +
                ", isVerified=" + isVerified +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact_no='" + contact_no + '\'' +
                '}';
    }
}
