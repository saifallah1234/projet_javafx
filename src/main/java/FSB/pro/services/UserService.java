package FSB.pro.services;
import FSB.pro.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import FSB.pro.DAO.*;

public class UserService {
    
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean signupUser(String username, String firstname, String lastname, String email, String password, String confirmPassword, String phoneNumber, String role) {
    // Check if username and email are unique
    if (!isUsernameUnique(username)) {
        showAlert(Alert.AlertType.ERROR, "Error", "Username is not unique.");
        return false;
    }
    if (!isEmailUnique(email)) {
        showAlert(Alert.AlertType.ERROR, "Error", "Email is not unique.");
        return false;
    }

    // Check if email ends with "@gmail.com"
    if (!isGmailEmail(email)) {
        showAlert(Alert.AlertType.ERROR, "Error", "Email must end with @gmail.com.");
        return false;
    }

    // Check if password and confirm password match
    if (!password.equals(confirmPassword)) {
        showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match.");
        return false;
    }

    // Check if phone number has 8 digits
    if (!isValidPhoneNumber(phoneNumber)) {
        showAlert(Alert.AlertType.ERROR, "Error", "Phone number must have 8 digits.");
        return false;
    }

    // Everything is valid, create a new user
    User newUser = new User(username, firstname, lastname,email,  password, phoneNumber, role);
    userDAO.addUser(newUser);

    return true;  // Signup successful
}
    
    public boolean isValidPhoneNumber(String phoneNumber) {
        // Remove any non-digit characters
        String digitsOnly = phoneNumber.replaceAll("\\D", "");
        return digitsOnly.length() == 8; // Check if the phone number has 8 digits
    }
    
    
    
    private boolean isUsernameUnique(String username) {
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            if (user.getUsername()!=null && user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmailUnique(String email) {
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
    public boolean isGmailEmail(String email) {
        
        return email.toLowerCase().endsWith("@gmail.com");
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    

}
