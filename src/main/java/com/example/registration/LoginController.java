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

public class LoginController implements Initializable {

    @FXML
    private Button CancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView sideImageView;
    @FXML
    private ImageView loginImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private CheckBox termsAndCondition;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File sideImageFile = new File("images/image_login.png");
        Image sideImage = new Image(sideImageFile.toURI().toString());
        sideImageView.setImage(sideImage);

        File loginImageFile = new File("images/image_logo.png");
        Image loginImage = new Image(loginImageFile.toURI().toString());
        loginImageView.setImage(loginImage);
    }

    public void loginButtonPushed(ActionEvent event) throws SQLException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        try{
            if(usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false){

                if(!termsAndCondition.isDisable()){
                    if (DataSource.getInstance().loginValidation(username,password)){
                        loginMessageLabel.setText("Login Success!");
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/example/registration/home_page.fxml"));
                        Parent homePage = loader.load();

                        Scene homePageScene = new Scene(homePage);
                        HomeController controller = loader.getController();
//                        controller.initData(DataSource.getInstance().selectedUser(username));
                        controller.initData(username);
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(homePageScene);
                        window.show();
                    }else {
                        loginMessageLabel.setText("Wrong Email or password!");
                    }
                }else {
                    loginMessageLabel.setText("Accept T&C");
                }
                }else{
                loginMessageLabel.setText("Please enter username, password");
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    public void adminLoginButtonPushed(ActionEvent event) throws SQLException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        try{
            if(usernameTextField.getText().equalsIgnoreCase("ninadpatil")){

                if(!termsAndCondition.isDisable()){
                    if (DataSource.getInstance().loginValidation("ninadpatil",password)){
                        loginMessageLabel.setText("Login Success!");
                        Parent adminHomePage = FXMLLoader.load(getClass().getResource("/com/example/registration/admin_home_page.fxml"));
                        Scene adminHomePageScene = new Scene(adminHomePage);

                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(adminHomePageScene);
                        window.show();
                    }else {
                        loginMessageLabel.setText("Wrong Email or password!");
                    }
                }else {
                    loginMessageLabel.setText("Accept T&C");
                }
            }else{
                loginMessageLabel.setText("Please enter username, password");
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void registerButtonPushed(ActionEvent event) throws IOException{
        Parent registerPage = FXMLLoader.load(getClass().getResource("/com/example/registration/register_page.fxml"));
        Scene registerPageScene = new Scene(registerPage);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(registerPageScene);
        window.show();
    }

    @FXML
    public void cancelButtonPushed(ActionEvent event){
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }



}