package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Doctor;
import model.HistoryDesease;
import model.ModelTable;
import model.ModelTablePatients;
import sample.Const;
import sample.DataBaseHandler;

public class DoctorInfoContoller {
    private ModelTable selectedDoctor;

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
    private Text spec_field;

    @FXML
    private Text qual_field;

    @FXML
    private TableView<ModelTablePatients> patients_table;

    @FXML
    private TableColumn<ModelTablePatients, String> name_column;

    @FXML
    private TableColumn<ModelTablePatients, String> surn_column;

    @FXML
    private TableColumn<ModelTablePatients, String> last_name_column;

    @FXML
    private Button backButton1;

    ObservableList<ModelTablePatients> doclist = FXCollections.observableArrayList();

    @FXML
    void OnActionBackButton(ActionEvent event) {

    }

    @FXML
    void initialize() {


    }

    public void initData(ModelTable doctor) throws SQLException, ClassNotFoundException {
        selectedDoctor = doctor;
        name_field.setText(selectedDoctor.getName());
        surname_field.setText(selectedDoctor.getSurname());
        lastname_field.setText(selectedDoctor.getLastname());
        age_field.setText(selectedDoctor.getAge());

        String get_info = "SELECT distinct " + Const.PATIENTS_NAME + "," + Const.PATIENTS_SURNAME + "," + Const.PATIENTS_LASTNAME + ","
                + Const.PATIENTS_AGE + "," + Const.PATIENTS_DESEASE + "," + Const.PATIENTS_DOCTOR + "," + Const.PATIENTS_CHAMBER
                + " FROM " + Const.PATIENTS_TABLE
                + " WHERE " + "'" + Doctor.getSurname() + "'" + " = patients.attending_doctor_p;";
        DataBaseHandler db = new DataBaseHandler();
        Connection con = db.getDbConnection();
        ResultSet rs = con.createStatement().executeQuery(get_info);

        while(rs.next())
        {
            doclist.add(new ModelTablePatients(rs.getString("first_name_p"),rs.getString("surname_p"),rs.getString("last_name_p")));
        }

        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        surn_column.setCellValueFactory(new PropertyValueFactory<>("surname"));
        last_name_column.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        patients_table.setItems(doclist);


    }

}
