package com.example.samuelhimself.agent;

public class ExampleItem {
    private int mImageResource;
    private String Surname,Firstname,Phone,Pack,Cash;

    public ExampleItem(int imageResource, String surname, String firstname,String phone,String pack,String cash) {
        mImageResource = imageResource;
        Surname = surname;
        Firstname = firstname;
        Phone = phone;
        Pack = pack;
        Cash = cash;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getSurname() {
        return Surname;
    }

    public String getFirstname() {
        return Firstname;
    }
    public String getPhone() {
        return Phone;
    }

    public String getPack() {
        return Pack;
    }
    public String getCash() {
        return Cash;
    }

}