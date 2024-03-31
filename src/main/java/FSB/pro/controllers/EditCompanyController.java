package FSB.pro.controllers;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import FSB.pro.DAO.CompanyDAO;
import FSB.pro.DAO.UserDAO;
import FSB.pro.models.Company;
import FSB.pro.models.User;
import FSB.pro.utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class EditCompanyController {
   @FXML
    private ImageView userImageView;

    @FXML
    private ImageView closeButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label userOnlineCountLabel;

    @FXML
    private ListView<String> userOnlineList;

    @FXML
    private Button showProfile;

    @FXML
    private Button openChat;

    @FXML
    private Button handleNotificationButton;

    @FXML
    private Button handleSettingsButton;

    @FXML
    private Button handleLogout;

    @FXML
    private ImageView companyLogo;

    @FXML
    private TextField companyNameField;

    @FXML
    private TextArea companyBioArea;

    @FXML
    private ListView<String> servicesListView;

    @FXML
    private ListView<String> teamListView;

    @FXML
    private ListView<String> companyProjectsListView;
    public CompanyDAO companyDAO = new CompanyDAO();
    public UserDAO userDAO = new UserDAO();
    private long companyId;
    public Company companyProfile = companyDAO.getCompanyById(companyId);
    public void companyId(long companyId) {
        this.companyId = companyId;
    }
    



    @FXML
    private void initialize() {
        // Initialize user information
        companyProfile = companyDAO.getCompanyById(companyId);
        userImageView.setImage(fetchProfileImage());
        usernameLabel.setText(fetchUsername());
        userOnlineCountLabel.setText("Online: " + fetchOnlineCount());
        
    }

   private Image fetchProfileImage() {
        if (companyProfile != null) {
            String imagePath = companyProfile.getLogo(); // Assuming this is the file path stored in the database
            File imageFile = new File(imagePath);
    
            if (imageFile.exists()) {
                return new Image(imageFile.toURI().toString());
            } else {
                // Default image if the file doesn't exist
                return new Image("/path/to/default/image.png");
            }
        }
    
        // Return null or default image if user not found or image path is null
        return new Image("/path/to/default/image.png");
    }

    private String fetchUsername() {
        return companyDAO.getCompanyById(companyId).getName();
    }
    private int fetchOnlineCount() {
        // Implement logic to fetch online count
        return userDAO.getAllUsers().stream().filter(x->x.isLogged()).collect(Collectors.toList()).size(); // Return the fetched online count
    }

    @FXML
    private void showProfile() {
       Stage currentStage = (Stage) showProfile.getScene().getWindow();
            UserCompanyProfile profileController = SceneSwitcher.switchScene("UserCompanyProfile.fxml", currentStage);
            profileController.userId(companyId);
    }

    @FXML
    private void openChat() {
        Stage currentStage = (Stage) openChat.getScene().getWindow();
        ChatController chatController = SceneSwitcher.switchScene("ChatView.fxml", currentStage);
        chatController.userId(companyId);
    }

    @FXML
    private void handleNotificationButton() {
        // Implement logic to handle notifications
    }

    @FXML
    private void handleSettingsButton() {
        // Implement logic to handle settings
    }

    @FXML
    private void handleLogout() {
        // Implement logic to handle logout
    }

    @FXML
    private void closeApplication() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

@FXML
private String browseForImage() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Company Logo");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
    );
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        javafx.scene.image.Image image = new javafx.scene.image.Image(selectedFile.toURI().toString());
        companyLogo.setImage(image);
        return selectedFile.toURI().toString();
    }
    return null;
}


    @FXML
    private void editImage() {
        companyProfile.setLogo(browseForImage());
    }

    @FXML
    private void editCompanyName() {
        companyProfile.setName(companyNameField.getText());
    }

    @FXML
    private void editCompanyBio() {
        companyProfile.setDescription(companyBioArea.getText());
    }

    @FXML
    private void editServices() {
        List<String> services = companyDAO.getAllServices(companyId);
        servicesListView.getItems().addAll(services);
    }

    @FXML
    private void editCompanyProjects() {
        List<String> projects = companyDAO.getAllProjects(companyId);
        companyProjectsListView.getItems().addAll(projects);
    }
    private void saveCompanyProfile() {
        companyDAO.updateCompany(companyProfile);
    }


    @FXML
    private void returnToMainProfile() {
        Stage currentStage = (Stage) showProfile.getScene().getWindow();
        UserCompanyProfile profileController = SceneSwitcher.switchScene("UserCompanyProfile.fxml", currentStage);
        profileController.userId(companyId);
    }
}
