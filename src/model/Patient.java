package model;

public class Patient {
    private String firstName;
    private String surname;
    private String lastName;
    private String age;
    private String desease;
    private String attending_doctor;
    private String chamber;
    private Boolean status = true;

    public Patient(String firstName, String surname, String lastName, String age, String desease, String attending_doctor, String chamber) {
        this.firstName = firstName;
        this.surname = surname;
        this.lastName = lastName;
        this.age = age;
        this.desease = desease;
        this.attending_doctor = attending_doctor;
        this.chamber = chamber;
    }

    public Patient() {}


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setAttending_doctor(String attending_doctor) {
        this.attending_doctor = attending_doctor;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
