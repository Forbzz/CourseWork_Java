package Controller;

import java.io.IOException;
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
import model.Doctor;
import model.ModelTablePatients;
import sample.Const;
import sample.DataBaseHandler;
import sample.Main;

import javax.print.Doc;

public class ProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ModelTablePatients> patients_table;

    @FXML
    private TableColumn<ModelTablePatients, String> name_column;

    @FXML
    private TableColumn<ModelTablePatients, String> surn_column;

    @FXML
    private TableColumn<ModelTablePatients, String> last_name_column;

    @FXML
    private Text name_field;

    @FXML
    private Text surname_field;

    @FXML
    private Text lastname_field;

    @FXML
    private Text age_field;

    @FXML
    private TextField history;

    @FXML
    private Text spec_field;

    @FXML
    private Text qual_field;

    @FXML
    private Button backButton;

    ObservableList<ModelTablePatients> patlist = FXCollections.observableArrayList();


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        name_field.setText(Doctor.getFirstName());
        surname_field.setText(Doctor.getSurname());
        lastname_field.setText(Doctor.getLastName());
        age_field.setText(Doctor.getAge());
        spec_field.setText(Doctor.getSpeciality());
        qual_field.setText(Doctor.getQualification());

        String get_string = "SELECT " + "*" + " FROM " + Const.PATIENTS_TABLE;
        String get_info = "SELECT distinct " + Const.PATIENTS_NAME + "," + Const.PATIENTS_SURNAME + "," + Const.PATIENTS_LASTNAME + ","
                + Const.PATIENTS_AGE + "," + Const.PATIENTS_DESEASE + "," + Const.PATIENTS_DOCTOR + "," + Const.PATIENTS_CHAMBER
                + " FROM " + Const.PATIENTS_TABLE
                + " WHERE " + "'" + Doctor.getSurname() + "'" + " = patients.attending_doctor_p;";
        DataBaseHandler db = new DataBaseHandler();
        Connection con = db.getDbConnection();
        ResultSet rs = con.createStatement().executeQuery(get_info);

        while(rs.next())
        {
            patlist.add(new ModelTablePatients(rs.getString("first_name_p"),rs.getString("surname_p"),rs.getString("last_name_p"),
                    rs.getString("age_p"),rs.getString("desease_p"),rs.getString("attending_doctor_p") , rs.getString("chamber_p")));
        }

        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        surn_column.setCellValueFactory(new PropertyValueFactory<>("surname"));
        last_name_column.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        patients_table.setItems(patlist);


    }

    @FXML
    void OnActionBackButton() throws IOException {
        Main.showTable();
    }
}
