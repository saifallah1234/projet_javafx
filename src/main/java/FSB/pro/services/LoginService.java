package FSB.pro.services;

import java.io.IOException;

import FSB.pro.DAO.CompanyDAO;
import FSB.pro.DAO.UserDAO;
import FSB.pro.controllers.UserMainProfileController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LoginService {
    UserDAO userDAO;
    CompanyDAO companyDAO;
    public LoginService(UserDAO userDAO,CompanyDAO companyDAO) {
        this.userDAO = userDAO;
        this.companyDAO = companyDAO;
    }
    public long loginUser(String username, String password) {
        Long userId = 0L;
        
        if(userDAO.usernameExists(username)){
            userId = userDAO.getUserIdByUsernameAndPassword(username, password);
            
            if (userId != 0) {
                System.out.println("User exists with ID: " + userId);
                userProfile(userId);
                return userId;
            } 
        }
        
        if(companyDAO.companyNameExists(username)){
            userId = companyDAO.getCompanyIdByNameAndPassword(username, password);
            
            if (userId != 0) {
                System.out.println("company exists with ID: " + userId);
                //userProfile(userId);
                return userId;
            } 
        }
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("User or company does not exist");
        alert.setContentText("Please check your username and password.");
        alert.showAndWait();
        
        return 0;
    }
    
    private void userProfile(long userId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/userprofile.fxml"));
            UserMainProfileController controller = new UserMainProfileController(userId);
            loader.setController(controller);
            Parent profile = loader.load();
    
            Stage stage = new Stage();
            stage.setScene(new Scene(profile));
            stage.show();
    
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
