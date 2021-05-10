package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Doctor;
import sample.DataBaseHandler;
import sample.Main;



public class Controller {

    private Main main;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button login;

    @FXML
    private TextField login_field;


    @FXML
    private TextField password_field;

    @FXML
    private Button register;

    public void loginUser(String loginText, String passwordText) throws SQLException, IOException, ClassNotFoundException {
        DataBaseHandler dbHandler = new DataBaseHandler();
        Doctor doctor = new Doctor();
        doctor.setUsername(loginText);
        doctor.setPassword(passwordText);
        ResultSet result = dbHandler.getUser(doctor);

        int counter = 0;

        while(result.next())
        {
            counter++;
        }

        if(counter >= 1)
        {
            Main.showTable();
        }
        else
        {
            Shake login = new Shake(login_field);
            Shake password = new Shake(password_field);
            login.playAnim();
            password.playAnim();
        }

    }

    @FXML
    void OnActionRegister() throws IOException {
        Main.showReg();
    }
    @FXML
    void initialize() {

        register.setOnAction(actionEvent -> {
            System.out.println("Регистрация");
            try {
                OnActionRegister();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void OnActonLogin() throws IOException, SQLException, ClassNotFoundException {
        String loginText = login_field.getText().trim();
        String loginPassword = password_field.getText().trim();
        loginUser(loginText, loginPassword);
    }


}

