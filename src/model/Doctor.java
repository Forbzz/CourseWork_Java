package model;

public class Doctor {
    private static String firstName;
    private static String surname;
    private static String lastName;
    private static String age;
    private static String speciality;
    private static String qualification;
    private static String username;
    private static String password;

    public Doctor(String firstName, String surname, String lastName, String age, String speciality, String qualification, String username, String password) {
        Doctor.firstName = firstName;
        Doctor.surname = surname;
        Doctor.lastName = lastName;
        Doctor.age = age;
        Doctor.speciality = speciality;
        Doctor.qualification = qualification;
        Doctor.username = username;
        Doctor.password = password;
    }

    public Doctor() {

    }

    public static String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        Doctor.firstName = firstName;
    }

    public static String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        Doctor.surname = surname;
    }

    public static String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        Doctor.lastName = lastName;
    }

    public static String getAge() {
        return age;
    }

    public void setAge(String age) {
        Doctor.age = age;
    }

    public static String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        Doctor.speciality = speciality;
    }

    public static String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        Doctor.qualification = qualification;
    }

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Doctor.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Doctor.password = password;
    }
}
