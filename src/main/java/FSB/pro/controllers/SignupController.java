package FSB.pro.controllers;
import FSB.pro.models.User;

import java.io.IOException;

import FSB.pro.DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import FSB.pro.services.*;



public class SignupController {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private ChoiceBox<String> imagePicker;

    private UserDAO userDAO;

    @FXML
    public void initialize() {
        // Initialize the userDAO
        userDAO = new UserDAO();
        

        // Populate the imagePicker choice box
        imagePicker.getItems().addAll("user", "company");
        imagePicker.setValue("user");
    }

    @FXML
    public void signupButtonAction(ActionEvent event) {
        String username = usernameTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String phoneNumber = phoneTextField.getText();
        String role = imagePicker.getValue().equals("user") ? "USER" : "COMPANY";
    
        // Check for empty fields
        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Empty Fields", "Please fill all the fields");
            return;
        }
    
        // Check if username or email is already taken
        UserService userService = new UserService(userDAO);
        if (!userService.signupUser(username, firstName, lastName, email, password, confirmPassword, phoneNumber, role)) {
            showAlert(Alert.AlertType.ERROR, "Field Already Taken", "Username or Email is already taken.");
            return;
        }
    
        // Check for password match
        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Password Mismatch", "Passwords do not match.");
            return;
        }
    
        // Create a new user
        User newUser = new User(username, email, firstName, lastName, password, phoneNumber, role);
    
        // Add the new user to the database
        userDAO.addUser(newUser);
    
        showAlert(Alert.AlertType.INFORMATION, "Signup Success", "User signed up successfully!");
        // Close the window
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
    

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void minimizeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void closeSystem(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}