package model;

public class ModelTablePatients {
    String name,surname,lastname,age,desease,attending_doctor,chamber;

    public ModelTablePatients(String name, String surname, String lastname, String age, String desease, String attending_doctor, String chamber) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.age = age;
        this.desease = desease;
        this.attending_doctor = attending_doctor;
        this.chamber = chamber;
    }

    public ModelTablePatients(String name, String surname, String lastname, String age) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.age = age;
    }

    public ModelTablePatients(String first_name_p, String surname_p, String last_name_p) {
        this.name = first_name_p;
        this.surname = surname_p;
        this.lastname = last_name_p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDesease() {
        return desease;
    }

    public void setDesease(String desease) {
        this.desease = desease;
    }

    public String getAttending_doctor() {
        return attending_doctor;
    }

    public void setAttending_doctor(String attendign_doctor) {
        this.attending_doctor = attendign_doctor;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }
}
