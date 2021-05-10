package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Doctor;
import model.ModelTablePatients;
import model.Patient;

import java.sql.*;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException
    {
        String connectionString = "jdbc:mysql://"+ dbHost +":" + dbPort
                + "/" + dbName +  "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbConnection;

    }

    //
    public void signUpDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.DOCTOR_TABLE + "(" +
                Const.DOCTOR_NAME + "," + Const.DOCTOR_SURNAME + "," +
                Const.DOCTOR_LASTNAME + "," + Const.DOCTOR_AGE + "," +
                Const.DOCTOR_SPECIALITY + "," + Const.DOCTOR_QUALIFICATION + "," +
                Const.DOCTOR_USERNAME + "," + Const.DOCTOR_PASSWORD + ")" +
                "VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, doctor.getFirstName());
            prSt.setString(2, doctor.getSurname());
            prSt.setString(3, doctor.getLastName());
            prSt.setString(4, doctor.getAge());
            prSt.setString(5, doctor.getSpeciality());
            prSt.setString(6, doctor.getQualification());
            prSt.setString(7, doctor.getUsername());
            prSt.setString(8, doctor.getPassword());

            prSt.executeUpdate();
        } catch(SQLException e)
        {
            e.printStackTrace();
        }


    }

    public ResultSet getUser(Doctor doctor) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.DOCTOR_TABLE + " WHERE " +
                Const.DOCTOR_USERNAME + "=? AND " + Const.DOCTOR_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, doctor.getUsername());
            prSt.setString(2, doctor.getPassword());


            resSet = prSt.executeQuery();
        } catch(SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        String get_info = "SELECT * FROM " + Const.DOCTOR_TABLE + " WHERE " +
                Const.DOCTOR_USERNAME + " = " + "'" +doctor.getUsername() + "'" + " AND " + Const.DOCTOR_PASSWORD + " = " + "'" + doctor.getPassword() + "'";

        DataBaseHandler db = new DataBaseHandler();
        Connection con = db.getDbConnection();
        ResultSet rs = con.createStatement().executeQuery(get_info);

        while(rs.next())
        {
            doctor.setFirstName(rs.getString("first_name"));
            doctor.setSurname(rs.getString("surname"));
            doctor.setLastName(rs.getString("last_name"));
            doctor.setAge(rs.getString("age"));
            doctor.setSpeciality(rs.getString("speciality"));
            doctor.setQualification(rs.getString("qualification"));
        }

        return resSet;
    }

    public void addPatient(Patient patient) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.PATIENTS_TABLE + "(" +
                Const.PATIENTS_NAME + "," + Const.PATIENTS_SURNAME + "," +
                Const.PATIENTS_LASTNAME + "," + Const.PATIENTS_AGE + "," +
                Const.PATIENTS_DESEASE + "," + Const.PATIENTS_DOCTOR + "," +
                Const.PATIENTS_CHAMBER + "," + Const.PATIENTS_HISTORY +")" +
                "VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, patient.getFirstName());
            prSt.setString(2, patient.getSurname());
            prSt.setString(3, patient.getLastName());
            prSt.setString(4, patient.getAge());
            prSt.setString(5, patient.getDesease());
            prSt.setString(6, patient.getAttending_doctor());
            prSt.setString(7, patient.getChamber());
            prSt.setString(8, patient.getDesease());


            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void returnPatient(Patient patient) throws SQLException, ClassNotFoundException {
        String update = "UPDATE hospital.patients SET desease_p = " + "'" + patient.getDesease() + "'," +
                "attending_doctor_p = " + "'" + patient.getAttending_doctor() + "'," +
                "first_name_p = " + "'" + patient.getFirstName() + "'," +
                "surname_p = " + "'" + patient.getSurname() + "'," +
                "last_name_p = " + "'" + patient.getLastName() + "'," +
                "age_p = " + "'" + patient.getAge() + "'," +
                "chamber_p = " + "'" + patient.getAttending_doctor() + "'," +
                "history_desease_p = CONCAT(history_desease_p, " + "'," + patient.getDesease() + "') " +
                "WHERE surname_p = " + "'" + patient.getSurname() + "';";

        DataBaseHandler db = new DataBaseHandler();
        Connection con = db.getDbConnection();
        con.createStatement().executeUpdate(update);

    }

    public static ObservableList<ModelTablePatients> getDataPatients() throws SQLException, ClassNotFoundException {
        DataBaseHandler db = new DataBaseHandler();
        Connection con = db.getDbConnection();
        ObservableList<ModelTablePatients> list = FXCollections.observableArrayList();
        try
        {
            PreparedStatement ps = con.prepareStatement("SELECT " + Const.PATIENTS_NAME + "," + Const.PATIENTS_SURNAME + "," + Const.PATIENTS_LASTNAME + ","
                    + Const.PATIENTS_AGE +" FROM " + Const.PATIENTS_TABLE + " WHERE attending_doctor_p = '';");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                list.add(new ModelTablePatients(rs.getString("first_name_p"),rs.getString("surname_p"),rs.getString("last_name_p"),
                        rs.getString("age_p")));
            }

        }catch (Exception e){
            e.getStackTrace();
        }
        return list;
    }


}
