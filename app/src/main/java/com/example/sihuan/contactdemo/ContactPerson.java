package com.example.sihuan.contactdemo;

/**
 * ContactPerson
 * Created by SiHuan on 2017/4/6.
 */

public class ContactPerson {

    private String name;
    private String phone;
    private String Email;

    public ContactPerson() {

    }

    public ContactPerson(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        Email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
