package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Doctor;
import sample.DataBaseHandler;
import sample.Main;

public class RegController  {

    ObservableList<String> categoryList = FXCollections
            .observableArrayList("Интерн","Вторая категория","Первая категория","Высшая категория");

    @FXML
    private Button login;

    @FXML
    private TextField name_field;

    @FXML
    private TextField surname_field;

    @FXML
    private TextField login_field;

    @FXML
    private TextField password_field;

    @FXML
    private TextField last_name;

    @FXML
    private TextField age;

    @FXML
    private TextField speciality;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button backButton;


    @FXML
    void initialize() {
        comboBox.setItems(categoryList);
    }

    public void OnActionBackButton() throws IOException {

        Main.showMain();
    }


    public void Register(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {

        registerNewDoctor();
        OnActionBackButton();

    }

    private void showAlert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Не введены все данные");
        alert.setHeaderText("Ошибка регистрации");
        alert.setContentText("Пожалуйста, заполните все поля");
        alert.showAndWait();
    }

    private void registerNewDoctor() throws SQLException, ClassNotFoundException {
        DataBaseHandler dbHandler = new DataBaseHandler();

        String firstName = name_field.getText();
        String surname = surname_field.getText();
        String lastName = last_name.getText();
        String age_ = age.getText();
        String speciality_ = speciality.getText();
        String qualification = comboBox.getValue();
        String username = login_field.getText();
        String password = password_field.getText();

        if(name_field.getText().isEmpty() ||surname_field.getText().isEmpty() ||last_name.getText().isEmpty() ||age.getText().isEmpty() ||
                speciality.getText().isEmpty() ||comboBox.getSelectionModel().isEmpty() ||login_field.getText().isEmpty() ||password_field.getText().isEmpty())
        {
            showAlert();
            return;
        }

        Doctor doctor = new Doctor(firstName, surname, lastName, age_, speciality_, qualification, username, password);

        dbHandler.signUpDoctor(doctor);
    }
}
