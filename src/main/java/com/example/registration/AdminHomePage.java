package com.example.registration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminHomePage {

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label informationLabel;

    public void enterButtonPushed() throws SQLException{
        if(DataSource.getInstance().deleteUser(usernameTextField.getText())){
            informationLabel.setText("User has been deleted!!");
        }else {
            informationLabel.setText("Couldn't delete the User.");
        }
    }

    public void cancelButtonPressed(ActionEvent event) throws IOException {
        Parent loginPage = FXMLLoader.load(getClass().getResource("/com/example/registration/login_page.fxml"));
        Scene loginPageScene = new Scene(loginPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginPageScene);
        window.show();
    }
}
