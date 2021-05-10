package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Doctor;
import model.Patient;
import sample.DataBaseHandler;
import sample.Main;

public class AddPatientController {

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
    void initialize() {
        attending_doctor.setText(Doctor.getSurname());

    }

    @FXML
    void OnActionBackButton(ActionEvent event) throws IOException {
        Main.showTable();
    }

    private void showAlert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Не введены все данные");
        alert.setHeaderText("Ошибка регистрации");
        alert.setContentText("Пожалуйста, заполните все поля");
        alert.showAndWait();
    }

    @FXML
    void Register(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        DataBaseHandler dbHandler = new DataBaseHandler();

        String firstName = name_field.getText();
        String surname = surname_field.getText();
        String lastName = last_name.getText();
        String age_ = age.getText();
        String desease_ = desease.getText();
        String doctor = attending_doctor.getText();
        String chamber_ = chamber.getText();

        if(name_field.getText().isEmpty() ||surname_field.getText().isEmpty() ||last_name.getText().isEmpty() ||age.getText().isEmpty() ||
                desease.getText().isEmpty() ||attending_doctor.getText().isEmpty() ||chamber.getText().isEmpty())
        {
            showAlert();
            return;
        }

        Patient patient = new Patient(firstName, surname, lastName, age_, desease_, doctor, chamber_);
        dbHandler.addPatient(patient);

        Main.showTable();

    }
}
