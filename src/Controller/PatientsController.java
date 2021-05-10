package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ModelTablePatients;
import model.Patient;
import sample.Const;
import sample.DataBaseHandler;
import sample.Main;

public class PatientsController {
    private Main main;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ModelTablePatients> table_patients;

    @FXML
    private TableColumn<ModelTablePatients, String> col_name;

    @FXML
    private TableColumn<ModelTablePatients, String> col_surname;

    @FXML
    private TableColumn<ModelTablePatients, String> col_lastname;

    @FXML
    private TableColumn<ModelTablePatients, String> col_age;

    @FXML
    private TableColumn<ModelTablePatients, String> col_desease;

    @FXML
    private TableColumn<ModelTablePatients, String> col_doctor;

    @FXML
    private TableColumn<ModelTablePatients, String> col_chumber;

    @FXML
    private Button patients;

    @FXML
    private Button add_button;

    @FXML
    private Button chambers;

    @FXML
    private Button profile_button;

    @FXML
    private Button info_button;

    @FXML
    private Button staff;

    ObservableList<ModelTablePatients> patlist = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        table_patients.setRowFactory(tv->{
            TableRow<ModelTablePatients> row = new TableRow<>();
            row.setOnMouseClicked(event->
            {
                if (event.getClickCount() == 2 && (!row.isEmpty()))
                {
                    ModelTablePatients rowData = row.getItem();
                    Patient patient = new Patient(rowData.getName(),rowData.getSurname(),rowData.getLastname(),rowData.getAge(),rowData.getDesease(),rowData.getAttending_doctor(),rowData.getChamber());

                }
            });
            return row;
        });



        String get_string = "SELECT " + "*" + " FROM " + Const.PATIENTS_TABLE;
        String get_info = "SELECT distinct " + Const.PATIENTS_NAME + "," + Const.PATIENTS_SURNAME + "," + Const.PATIENTS_LASTNAME + ","
                + Const.PATIENTS_AGE + "," + Const.PATIENTS_DESEASE + "," + Const.PATIENTS_DOCTOR + "," + Const.PATIENTS_CHAMBER + ","
                + Const.DOCTOR_NAME + "," + Const.DOCTOR_SURNAME + "," + Const.DOCTOR_LASTNAME  + " FROM " + Const.DOCTOR_TABLE + "," + Const.PATIENTS_TABLE
                + " WHERE " + "doctors.surname = patients.attending_doctor_p;";
        DataBaseHandler db = new DataBaseHandler();
        Connection con = db.getDbConnection();
        ResultSet rs = con.createStatement().executeQuery(get_info);

        while(rs.next())
        {
            patlist.add(new ModelTablePatients(rs.getString("first_name_p"),rs.getString("surname_p"),rs.getString("last_name_p"),
                    rs.getString("age_p"),rs.getString("desease_p"),rs.getString("attending_doctor_p") + " " + (rs.getString("first_name")).charAt(0) + ". "+ (rs.getString("last_name")).charAt(0) + ".", rs.getString("chamber_p")));
        }
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_desease.setCellValueFactory(new PropertyValueFactory<>("desease"));
        col_doctor.setCellValueFactory(new PropertyValueFactory<>("attending_doctor"));
        table_patients.setItems(patlist);


    }

    @FXML
    void OnActionPatients(ActionEvent event) throws Exception {
        Main.showTable();
    }

    @FXML
    public void OnActionChambers(ActionEvent actionEvent) throws IOException {
        Main.showTable2();
    }

    @FXML
    public void OnActionStaff(ActionEvent actionEvent) throws IOException {
        Main.showTable3();

    }

    @FXML
    void OnActionInfoButton(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/info.fxml"));

        Parent root = loader.load();
        Stage stage = new Stage();

        PatientInfoController controller = loader.getController();
        controller.initData(table_patients.getSelectionModel().getSelectedItem());

        stage.setScene(new Scene(root,600,400));
        stage.show();

    }

    @FXML
    void OnActionAddButton(ActionEvent event) throws IOException {
        Main.showAddPatient();
    }

    @FXML
    void OnActionProfileButton(ActionEvent event) throws IOException {
        Main.showProfile();
    }
}
