package com.example.registration;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ImageView registerLogoImageView;
    @FXML
    private ImageView registerImageView;
    @FXML
    private Button cancelButton;
    @FXML
    private Label informationLabel;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField confirmTextField;
    @FXML
    private Label passwordMatchLabel;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    private ChoiceBox<String> genderChoiceBox;

    private String[] gender = {"Male","Female","Other"};
    String finalGender;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File registerLogoFile = new File("images/register_logo.png");
        Image registerLogoImage = new Image(registerLogoFile.toURI().toString());
        registerLogoImageView.setImage(registerLogoImage);

        File registerImageFile = new File("images/image_registration.png");
        Image registerImageImage = new Image(registerImageFile.toURI().toString());
        registerImageView.setImage(registerImageImage);

        genderChoiceBox.getItems().addAll(gender);
        genderChoiceBox.setOnAction(this::getGender);

    }

    public void getGender(ActionEvent event){
        String gender = genderChoiceBox.getValue();
        finalGender = gender;
    }

    public void cancelButtonPressed(ActionEvent event) throws IOException {
        Parent loginPage = FXMLLoader.load(getClass().getResource("/com/example/registration/login_page.fxml"));
        Scene loginPageScene = new Scene(loginPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginPageScene);
        window.show();
    }

    @FXML
    public void registerButtonPushed() throws SQLException {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String userName = userNameTextField.getText();
        String password = passwordTextField.getText();
        String number = numberTextField.getText();
        String selectedGender = finalGender;



        if(passwordTextField.getText().equals(confirmTextField.getText())){
            passwordMatchLabel.setText("Password Matches!!");
            if (DataSource.getInstance().insertUser(firstName,lastName,userName,password,selectedGender,number)){
                informationLabel.setText("Registration Successful!!");
            }else {
                informationLabel.setText("Registration not successful");
            }

        }else {
            passwordMatchLabel.setText("Password doesn't match");
        }

    }

}
