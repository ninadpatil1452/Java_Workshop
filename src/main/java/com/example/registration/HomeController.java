package com.example.registration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.PercentageStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

//    private User selectedUser;

    @FXML
    private Label userNameLabel;
    @FXML
    private TextField newNumber;
    @FXML
    private Label informationLabel;

    public void initData(String username){
//        selectedUser = user;
//        firstNameLabel.setText(selectedUser.getFirstName());
//        lastNameLabel.setText(selectedUser.getLastName());
//        userNameLabel.setText(selectedUser.getUsername());
        userNameLabel.setText(username);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void logoutButtonPressed(ActionEvent event) throws IOException {
        Parent loginPage = FXMLLoader.load(getClass().getResource("/com/example/registration/login_page.fxml"));
        Scene loginPageScene = new Scene(loginPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginPageScene);
        window.show();
    }

    public void changeNumberButtonPressed(ActionEvent event) throws SQLException {
        DataSource.getInstance().updateNumber(newNumber.getText(),userNameLabel.getText());
    }

}
