package com.example.registration;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class DataSource {

    public static final String DB_NAME ="registration.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\databases\\"+DB_NAME;

    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_USERID = "userid";
    public static final String COLUMN_USER_FIRSTNAME = "firstname";
    public static final String COLUMN_USER_LASTNAME = "lastname";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_GENDER = "gender";
    public static final String COLUMN_USER_NUMBER = "number";

    private static DataSource instance = new DataSource();

    public DataSource() {

    }

    public static DataSource getInstance(){
        return instance;
    }

    public static final String INSERT_USER =
            "INSERT INTO "+TABLE_USER+'('+COLUMN_USER_FIRSTNAME+", "+COLUMN_USER_LASTNAME+", "+
                    COLUMN_USER_USERNAME+", "+COLUMN_USER_PASSWORD+", "+COLUMN_USER_GENDER+", "+COLUMN_USER_NUMBER+
                    " ) VALUES (?,?,?,?,?,?)";

    public static final String QUERY_USER_DISPLAY =
            "SELECT "+TABLE_USER+'.'+COLUMN_USER_FIRSTNAME+", "+TABLE_USER+'.'+COLUMN_USER_LASTNAME+", "+TABLE_USER+'.'+
                    COLUMN_USER_GENDER+", "+TABLE_USER+'.'+COLUMN_USER_NUMBER+" FROM "+TABLE_USER;

    public static final String QUERY_USER_LOGIN =
            "SELECT * FROM "+TABLE_USER+" WHERE username = ? and password = ?";

    public static final String DELETE_USER =
            "DELETE FROM "+TABLE_USER+" WHERE username = ?";

    public static final String UPDATE_PHONE =
            "UPDATE "+TABLE_USER+" SET "+COLUMN_USER_NUMBER+" = (?) WHERE "+COLUMN_USER_USERNAME+" = (?)";

    public static final String QUERY_SELECTED_USER =
            "SELECT * FROM "+TABLE_USER+" WHERE username = ?";

    private Connection conn;

    private PreparedStatement insertUser;
    private PreparedStatement queryUserDisplay;
    private PreparedStatement queryUserLogin;
    private PreparedStatement deleteUser;
    private PreparedStatement updateNumber;
    private PreparedStatement querySelectedUser;

    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            insertUser = conn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            queryUserDisplay = conn.prepareStatement(QUERY_USER_DISPLAY);
            queryUserLogin = conn.prepareStatement(QUERY_USER_LOGIN, Statement.RETURN_GENERATED_KEYS);
            deleteUser = conn.prepareStatement(DELETE_USER,Statement.RETURN_GENERATED_KEYS);
            updateNumber = conn.prepareStatement(UPDATE_PHONE,Statement.RETURN_GENERATED_KEYS);
            querySelectedUser = conn.prepareStatement(QUERY_SELECTED_USER,Statement.RETURN_GENERATED_KEYS);
            return true;

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't connect to database at open  "+e.getMessage());
            return false;
        }
    }

    public void close(){
        try {
            if (insertUser!=null){
                insertUser.close();
            }
            if (queryUserDisplay!=null){
                queryUserDisplay.close();
            }
            if(queryUserLogin!=null){
                queryUserLogin.close();
            }
            if (deleteUser!=null){
                deleteUser.close();
            }
            if (updateNumber!=null){
                updateNumber.close();
            }
            if(querySelectedUser!=null){
                querySelectedUser.close();
            }
            if (conn!=null){
                conn.close();
            }
        }catch (SQLException e){
            System.out.println("Couldn't close the connection "+e.getMessage());
        }
    }

    public boolean insertUser (String firstname, String lastname, String username, String password, String gender, String number) throws SQLException{
        insertUser.setString(1,firstname);
        insertUser.setString(2,lastname);
        insertUser.setString(3,username);
        insertUser.setString(4,password);
        insertUser.setString(5,gender);
        insertUser.setString(6,number);
        int result = insertUser.executeUpdate();

        if (result!=1){
            return false;
//            throw new SQLException("Couldn't insert User!");
        }else {
            return true;
        }
        
    }

    public User selectedUser (String username) throws SQLException{
        querySelectedUser.setString(1,username);

        ResultSet result =querySelectedUser.executeQuery();

        User user = new User();
        try{
            user.setFirstName(result.getString("firstname"));
            user.setLastName(result.getString("lastname"));
            user.setUsername(result.getString("username"));
            user.setGender(result.getString("gender"));
            user.setNumber(result.getString("number"));

        }catch (NullPointerException e){
            System.out.println("Null pointer exception occurs: "+e.getMessage());
        }

        return user;
    }

    public boolean deleteUser (String username)throws SQLException{
        deleteUser.setString(1,username);
        int result = deleteUser.executeUpdate();

        if(result!=1){
            return false;
        }else {
            return true;
        }
    }

    public ObservableList<User> queryUserList(){

        try(Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(QUERY_USER_DISPLAY)){
            ObservableList<User> users = FXCollections.observableArrayList();

            while (result.next()){
                users.add(new User(result.getInt("userid"),result.getString("firstname"),
                        result.getString("lastname"), result.getString("username"), result.getString("password"),
                        result.getString("gender"), result.getString("number")));
            }
            return users;

        }catch (SQLException e){
            System.out.println("Query failed: "+e.getMessage());
            return null;

        }
    }

    public boolean loginValidation(String username, String password) throws SQLException{

        queryUserLogin.setString(1,username);
        queryUserLogin.setString(2,password);

        try{
            ResultSet result = queryUserLogin.executeQuery();

            if (result.next()){
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
            return false;
        }
    }

    public void updateNumber(String username, String newNumber) throws SQLException {
        updateNumber.setString(1, newNumber);
        updateNumber.setString(2, username);

        int ans = updateNumber.executeUpdate();

        if (ans!=1){
            throw new SQLException("Couldn't update number");
        }

    }
}
