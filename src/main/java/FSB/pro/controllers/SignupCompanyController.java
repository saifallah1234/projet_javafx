package FSB.pro.controllers;

import java.io.IOException;

import FSB.pro.DAO.CompanyDAO;
import FSB.pro.models.Company;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignupCompanyController {
     @FXML
    private TextField nameTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField companyEmailTextField;

    @FXML
    private TextField websiteTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField companyPhoneTextField;

    @FXML
    private Button signupBtn;

    @FXML
    private ImageView Defaultview;

    @FXML
    private ImageView companyview;

    @FXML
    private ImageView userview;

    private CompanyDAO companyDAO;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void initialize() {
        companyDAO = new CompanyDAO();
    }

    @FXML
    private void signupButtonAction(MouseEvent event) {
        String name = nameTextField.getText();
        String description = descriptionTextField.getText();
        String email = companyEmailTextField.getText();
        String website = websiteTextField.getText();
        String location = locationTextField.getText();
        String phoneNumber = companyPhoneTextField.getText();

        Company newCompany = new Company();
        newCompany.setName(name);
        newCompany.setDescription(description);
        newCompany.setEmail(email);
        newCompany.setWebsite(website);
        newCompany.setLocation(location);
        newCompany.setPhoneNumber(phoneNumber);

        companyDAO.addCompany(newCompany);

        // Switch to the company profile interface
        switchToCompanyProfile(newCompany.getId());
    }

    private void switchToCompanyProfile(long companyId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/views/CompanyProfile.fxml"));
            Parent root = loader.load();

            CompanyProfileController companyProfileController = loader.getController();
            companyProfileController.setCompanyId(companyId);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void minimizeWindow(MouseEvent event) {
        System.out.println("Minimize button clicked");
    }

    @FXML
    private void closeSystem(MouseEvent event) {
        System.out.println("Close button clicked");
    }

}
}
