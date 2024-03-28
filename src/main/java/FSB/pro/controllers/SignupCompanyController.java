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
import javafx.scene.control.Label;


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
    private ImageView Defaultview;

    @FXML
    private ImageView companyview;

    @FXML
    private ImageView userview;

    @FXML
    private Button signupBtn;

    @FXML
    private Button MinimizedBtn;

    @FXML
    private Button CloseBtn;

    @FXML
    private Label errorLabel;

    @FXML
    private void initialize() {
        errorLabel.setText("");
    }

    @FXML
    private void closeSystem() {
        Stage stage = (Stage) CloseBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimizeWindow(MouseEvent event) {
        Stage stage = (Stage) MinimizedBtn.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void signupButtonAction() {
        String name = nameTextField.getText();
        String description = descriptionTextField.getText();
        String email = companyEmailTextField.getText();
        String website = websiteTextField.getText();
        String location = locationTextField.getText();
        String phone = companyPhoneTextField.getText();

        if (validateFields(name, description, email, website, location, phone)) {
            CompanyDAO companyDAO = new CompanyDAO();
            Company newCompany = new Company();
            newCompany.setName(name);
            newCompany.setDescription(description);
            newCompany.setEmail(email);
            newCompany.setWebsite(website);
            newCompany.setLocation(location);
            newCompany.setPhoneNumber(phone);

            companyDAO.addCompany(newCompany);

            // Switch to the company profile interface
            switchToCompanyProfile(newCompany.getId());
        } else {
            errorLabel.setText("Please fill in all fields.");
        }
    }

    private void switchToCompanyProfile(long companyId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/CompanyProfile.fxml"));
            Parent root = loader.load();

            CompanyProfileController companyProfileController = loader.getController();
            companyProfileController.setCompanyId(companyId);

            Scene scene = new Scene(root);
            Stage stage = (Stage) signupBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    private boolean validateFields(String name, String description, String email, String website, String location, String phone) {
        return !name.isEmpty() && !description.isEmpty() && !email.isEmpty() && !website.isEmpty() && !location.isEmpty() && !phone.isEmpty();
    }


}
