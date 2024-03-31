package FSB.pro.services;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import FSB.pro.DAO.CompanyDAO;
import FSB.pro.models.Company;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CompanyService {

    private static final Logger LOGGER = Logger.getLogger(CompanyService.class.getName());

    public boolean companyTest(String name, String description, String email, String website, String location, String phone, String password) {
        CompanyDAO companyDAO = new CompanyDAO();

        try {
            // Check if a company with the given name, email, website, or location already exists
            if (companyDAO.getCompanyByName(name) != null
                    || companyDAO.getCompanyByEmail(email) != null
                    || companyDAO.getCompanyByWebsite(website) != null
                    || companyDAO.getCompanyByLocation(location) != null) {
                showAlert(AlertType.INFORMATION, "Company Exists", "Company already exists.");
                return true; // Company with same name, email, website, or location already exists
            } else {
                // Additional validations for email, website, and phone number format
                if (!isValidEmail(email)) {
                    showAlert(AlertType.ERROR, "Invalid Email", "Invalid email format.");
                    return true; // Invalid email format
                }
                if (!isValidWebsite(website)) {
                    showAlert(AlertType.ERROR, "Invalid Website", "Invalid website format.");
                    return true; // Invalid website format
                }
                if (!isValidPhoneNumber(phone)) {
                    showAlert(AlertType.ERROR, "Invalid Phone Number", "Invalid phone number format.");
                    return true; // Invalid phone number format
                }

                // Company does not exist, add the new company
                Company company = new Company(name, description, email, website, location, phone, password);
                companyDAO.addCompany(company);
                showAlert(AlertType.INFORMATION, "Company Added", "Company added successfully.");
                return false; // Company added successfully
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding company: " + e.getMessage());
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Failed to add company due to SQL exception.");
            return true; // Failed to add company due to SQL exception
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception adding company: " + ex.getMessage());
            ex.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Failed to add company due to unknown exception.");
            return true; // Failed to add company due to unknown exception
        }
    }

    private boolean isValidEmail(String email) {
        // Check if the email format is valid (ends with @gmail.com)
        return email.toLowerCase().endsWith("@gmail.com");
    }

    private boolean isValidWebsite(String website) {
        // Check if the website format is valid (starts with https:// and ends with .com)
        return website.toLowerCase().startsWith("https://") && website.toLowerCase().endsWith(".com");
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Remove any non-digit characters
        String digitsOnly = phoneNumber.replaceAll("\\D", "");
        return digitsOnly.length() == 8; // Check if the phone number has 8 digits
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
