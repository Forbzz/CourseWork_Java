package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Doctor;
import model.ModelTable;
import sample.DataBaseHandler;
import sample.Main;

public class MainController {
    private Main main;

    @FXML
    private ResourceBundle resources;

    @FXML
    private TableView<ModelTable> table_doctors;

    @FXML
    private TableColumn<ModelTable, String> col_name;

    @FXML
    private TableColumn<ModelTable, String> col_surname;

    @FXML
    private TableColumn<ModelTable, String> col_lastname;

    @FXML
    private TableColumn<ModelTable, String> col_age;

    @FXML
    private TableColumn<ModelTable, String> col_spec;

    @FXML
    private TableColumn<ModelTable, String> col_qual;

    @FXML
    private URL location;

    @FXML
    private TableView<?> table_patients;

    @FXML
    private TableView<?> table_palats;


    @FXML
    private Button patients;

    @FXML
    private Button chambers;

    @FXML
    private Button staff;

    @FXML
    void initialize() {


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


}
