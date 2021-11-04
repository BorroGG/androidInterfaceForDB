package com.example.testproject;

import com.example.testproject.db.entities.Judge;
import com.example.testproject.db.entities.Organ_employee;

public class UserData {
    public static int ROLE_ID = -1;
    public static Organ_employee CURRENT_USER_EMPL = null;
    public static Judge CURRENT_USER_JUDGE = null;
    //public static String YOU_LOG_AS = "";

    public static boolean isDutyOfficer() {
        if (ROLE_ID == 2) {
            return false;
        } else return CURRENT_USER_EMPL.position.equals("Дежурный в ДЧ");
    }
    private static String createYOU_LOG_AS() {
        if (CURRENT_USER_EMPL == null && CURRENT_USER_JUDGE == null) {
            return "";
        }
        return "Вы вошли как: " + (UserData.ROLE_ID == 2 ? UserData.CURRENT_USER_JUDGE.login : UserData.CURRENT_USER_EMPL.login);
    }

    public static String getYouLogAs() {
        return createYOU_LOG_AS();
    }

    public static String getLogin() {
        return UserData.ROLE_ID == 2 ? UserData.CURRENT_USER_JUDGE.login : UserData.CURRENT_USER_EMPL.login;
    }
}
