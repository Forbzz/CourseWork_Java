package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ModelTablePatients;
import sample.Const;
import sample.DataBaseHandler;
import sample.Main;

public class AllPatientsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ModelTablePatients> table_all_patients;

    @FXML
    private TableColumn<ModelTablePatients, String> name_field;

    @FXML
    private TableColumn<ModelTablePatients, String> surname_column;

    @FXML
    private TableColumn<ModelTablePatients, String > last_name_column;

    @FXML
    private TableColumn<ModelTablePatients, String> age_column;

    @FXML
    private Button patients;

    @FXML
    private Button return_patient;

    @FXML
    private Button chambers;

    @FXML
    private Button staff;

    @FXML
    private TextField filterField;

    ObservableList<ModelTablePatients> listM;
    ObservableList<ModelTablePatients> dataList;

    ObservableList<ModelTablePatients> patlist = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        String get_info = "SELECT " + Const.PATIENTS_NAME + "," + Const.PATIENTS_SURNAME + "," + Const.PATIENTS_LASTNAME + ","
                + Const.PATIENTS_AGE +" FROM " + Const.PATIENTS_TABLE + " WHERE attending_doctor_p = '';";
        DataBaseHandler db = new DataBaseHandler();
        Connection con = db.getDbConnection();
        ResultSet rs = con.createStatement().executeQuery(get_info);

        while (rs.next())
        {
            patlist.add(new ModelTablePatients(rs.getString("first_name_p"),rs.getString("surname_p"),rs.getString("last_name_p"),
                    rs.getString("age_p")));
        }

        name_field.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname_column.setCellValueFactory(new PropertyValueFactory<>("surname"));
        last_name_column.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        age_column.setCellValueFactory(new PropertyValueFactory<>("age"));

        table_all_patients.setItems(patlist);

        search_patient();
    }

    @FXML
    void OnActionPatients(ActionEvent event) throws Exception {
        Main.showTable();
    }

    public void OnActionChambers(ActionEvent actionEvent) throws IOException {
        Main.showTable2();
    }

    public void OnActionStaff(ActionEvent actionEvent) throws IOException {
        Main.showTable3();

    }

    @FXML
    void OnActionReturnPatient(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/return_patient.fxml"));

        Parent root = loader.load();
        Stage stage = new Stage();

        ReturnPatientController controller = loader.getController();
        controller.initData(table_all_patients.getSelectionModel().getSelectedItem());


        stage.setScene(new Scene(root,800,400));
        stage.show();

    }

    void search_patient() throws SQLException, ClassNotFoundException {
        name_field.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname_column.setCellValueFactory(new PropertyValueFactory<>("surname"));
        last_name_column.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        age_column.setCellValueFactory(new PropertyValueFactory<>("age"));

        dataList = DataBaseHandler.getDataPatients();
        table_all_patients.setItems(dataList);
        FilteredList<ModelTablePatients> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (person.getSurname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (person.getLastname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (String.valueOf(person.getAge()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<ModelTablePatients> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_all_patients.comparatorProperty());
        table_all_patients.setItems(sortedData);

    }


}
