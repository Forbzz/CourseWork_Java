package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Doctor;
import model.HistoryDesease;
import model.ModelTablePatients;
import model.Patient;
import sample.DataBaseHandler;

public class ReturnPatientController {
    private ModelTablePatients selectedPatient;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button login;

    @FXML
    private TextField name_field;

    @FXML
    private TextField surname_field;

    @FXML
    private TextField desease;

    @FXML
    private TextField attending_doctor;

    @FXML
    private TextField last_name;

    @FXML
    private TextField age;

    @FXML
    private TextField chamber;

    @FXML
    private Button backButton;

    @FXML
    void OnActionBackButton(ActionEvent event) {

    }

    @FXML
    void initialize() {
        attending_doctor.setText(Doctor.getSurname());

    }

    public void initData(ModelTablePatients patient) throws SQLException, ClassNotFoundException {
        selectedPatient = patient;
        name_field.setText(selectedPatient.getName());
        surname_field.setText(selectedPatient.getSurname());
        last_name.setText(selectedPatient.getLastname());
        age.setText(selectedPatient.getAge());

    }

    @FXML
    void Register(ActionEvent event) throws SQLException, ClassNotFoundException {
        DataBaseHandler dbHandler = new DataBaseHandler();

        String firstName = name_field.getText();
        String surname = surname_field.getText();
        String lastName = last_name.getText();
        String age_ = age.getText();
        String desease_ = desease.getText();
        String doctor = attending_doctor.getText();
        String chamber_ = chamber.getText();

        Patient patient = new Patient(firstName, surname, lastName, age_, desease_, doctor, chamber_);
        dbHandler.returnPatient(patient);

        Stage stage = (Stage)login.getScene().getWindow();
        stage.close();
    }


}
