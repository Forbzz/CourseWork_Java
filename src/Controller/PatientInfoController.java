package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.HistoryDesease;
import model.ModelTablePatients;
import model.Patient;
import sample.Const;
import sample.DataBaseHandler;
import sample.Main;

public class PatientInfoController {
    private ModelTablePatients selectedPatient;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text name_field;

    @FXML
    private Text surname_field;

    @FXML
    private Text lastname_field;

    @FXML
    private Text age_field;

    @FXML
    private Text doctor_field;

    @FXML
    private Button write_out;

    @FXML
    private TableView<HistoryDesease> table_deseases;

    @FXML
    private TableColumn<HistoryDesease, String> desease_column;

    ObservableList<HistoryDesease> patlist = FXCollections.observableArrayList();

    @FXML
    void initialize() {


    }

    public void initData(ModelTablePatients patient) throws SQLException, ClassNotFoundException {
        selectedPatient = patient;
        name_field.setText(selectedPatient.getName());
        surname_field.setText(selectedPatient.getSurname());
        lastname_field.setText(selectedPatient.getLastname());
        doctor_field.setText(selectedPatient.getAttending_doctor());
        age_field.setText(selectedPatient.getAge());

        String info = "SELECT history_desease_p FROM hospital.patients WHERE surname_p = " + "'" +selectedPatient.getSurname() + "'" + ";";

        DataBaseHandler db = new DataBaseHandler();
        Connection con = db.getDbConnection();
        ResultSet rs = con.createStatement().executeQuery(info);
        String history = null;

        while(rs.next()) {
            history = (rs.getString("history_desease_p"));
        }

        String[] history_mass = history.split(",");
        for(String words:history_mass)
        {
            patlist.add(new HistoryDesease(words));
        }
        desease_column.setCellValueFactory(new PropertyValueFactory<>("history_desease"));
        table_deseases.setItems(patlist);
    }

    @FXML
    void OnActionWriteOut() throws SQLException, ClassNotFoundException {
        String update ="UPDATE hospital.patients SET " + Const.PATIENTS_CHAMBER + " = " + "'', " +
                Const.PATIENTS_DOCTOR + " = " + "'', " + Const.PATIENTS_DESEASE + " = " + "'' " +
                "WHERE " + Const.PATIENTS_SURNAME + " = " + surname_field.getText();

        String update2 ="UPDATE hospital.patients SET chamber_p = '', attending_doctor_p = '', desease_p = '' WHERE surname_p = " + "'" + surname_field.getText() + "';";

        DataBaseHandler db = new DataBaseHandler();
        Connection con = db.getDbConnection();
        con.createStatement().executeUpdate(update2);

        Stage stage = (Stage)write_out.getScene().getWindow();
        stage.close();



    }
    
}
