package FSB.pro.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import java.util.List;

import FSB.pro.DAO.CompanyDAO;
import FSB.pro.models.Company;

public class CompanyProfileController {

    @FXML
    private ImageView companyLogo;

    @FXML
    private Label companyNameLabel;

    @FXML
    private Label companyBioLabel;

    @FXML
    private Button followButton;

    @FXML
    private ListView<String> servicesListView;

    @FXML
    private ListView<String> teamListView;

    @FXML
    private ListView<String> companyProjectsListView;

    @FXML
    private Button likeButton;

    @FXML
    private Label likeCountLabel;

    @FXML
    private Button viewJobOpeningsButton;

    @FXML
    private ListView<String> companyNewsListView;

    private CompanyDAO companyDAO;

    private long companyId;

    public void initialize() {
        companyDAO = new CompanyDAO();
        displayCompanyProfile();
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    private void displayCompanyProfile() {
        Company company = companyDAO.getCompanyById(companyId);
        if (company != null) {
            companyNameLabel.setText(company.getName());
            companyBioLabel.setText(company.getDescription());
            // Display other company details as needed
        }
    }

    @FXML
    private void handleFollowButtonAction() {
        // Implement follow company functionality
    }

    @FXML
    private void handleLikeButtonAction() {
        // Implement like company functionality
    }

    @FXML
    private void handleViewJobOpeningsButtonAction() {
        // Implement view job openings functionality
    }

    @FXML
    private void handleLikeNewsButtonAction() {
        // Implement like news functionality
    }

    @FXML
    private void handleCommentNewsButtonAction() {
        // Implement comment on news functionality
    }

    @FXML
    private void handleShareNewsButtonAction() {
        // Implement share news functionality
    }
}
