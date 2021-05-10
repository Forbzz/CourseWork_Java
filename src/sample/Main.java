package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Авторизация");
        showMainView();

    }

    private void showMainView() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../View/sample_border.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showMain() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../View/sample_border.fxml"));
        BorderPane table2 = loader.load();
        mainLayout.setCenter(table2);
    }

    public static void showTable() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../View/table_patients.fxml"));
        BorderPane table2 = loader.load();
        mainLayout.setCenter(table2);
    }

    public static void showTable2() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../View/allpatients_table.fxml"));
        BorderPane table2 = loader.load();
        mainLayout.setCenter(table2);
    }

    public static void showTable3() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../View/persobal_table.fxml"));
        BorderPane table2 = loader.load();
        mainLayout.setCenter(table2);

    }

    public static void showReg() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../View/registration.fxml"));
        BorderPane table2 = loader.load();
        mainLayout.setCenter(table2);

    }

    public static void showAddPatient() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../View/add_patient.fxml"));
        BorderPane table2 = loader.load();
        mainLayout.setCenter(table2);

    }

    public static void showProfile() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../View/profile.fxml"));
        BorderPane table2 = loader.load();
        mainLayout.setCenter(table2);

    }


    public static void main(String[] args) {
        launch(args);
    }

}
