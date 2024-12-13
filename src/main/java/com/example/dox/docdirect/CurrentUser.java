package com.example.dox.docdirect;

public class CurrentUser {
    public static String phoneNumber;
    public static String userName;
    public static String email;
    public static String DateOfBirth;
    public static String gender;


    public static void setUserData(String phoneNumber, String userName, String email, String DateOfBirth, String gender) {
        CurrentUser.phoneNumber = phoneNumber;
        CurrentUser.userName = userName;
        CurrentUser.email = email;
        CurrentUser.DateOfBirth = DateOfBirth;
        CurrentUser.gender = gender;
    }
}
