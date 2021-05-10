package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ModelTable;
import sample.Const;
import sample.DataBaseHandler;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DoctorTableController {
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
    private Button info_button;

    @FXML
    private Button patients;

    @FXML
    private Button chambers;

    @FXML
    private Button staff;

    ObservableList<ModelTable> doclist = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        String get_string = "SELECT "+"*"+" FROM "+ Const.DOCTOR_TABLE;
        DataBaseHandler db = new DataBaseHandler();
        Connection con = db.getDbConnection();
        ResultSet rs = con.createStatement().executeQuery(get_string);

        while(rs.next())
        {
            doclist.add(new ModelTable(rs.getString("first_name"),rs.getString("surname"),rs.getString("last_name"),
                    rs.getString("age"),rs.getString("speciality"),rs.getString("qualification")));
        }

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_spec.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        col_qual.setCellValueFactory(new PropertyValueFactory<>("qualification"));

        table_doctors.setItems(doclist);

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
    void OnActionInfoButton(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/doctor_info.fxml"));

        Parent root = loader.load();
        Stage stage = new Stage();

        DoctorInfoContoller controller = loader.getController();
        controller.initData(table_doctors.getSelectionModel().getSelectedItem());

        stage.setScene(new Scene(root,800,400));
        stage.show();
    }
}
