package FSB.pro.controllers;

import java.io.File;
import java.util.List;

import FSB.pro.DAO.UserDAO;
import FSB.pro.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class EditUserProfileController {
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
    private ImageView userpicture;

    @FXML
    private MediaView videoView;

    @FXML
    private TextField NameField;

    @FXML
    private TextArea BioArea;

    @FXML
    private TextArea skillsTextarea;

    @FXML
    private TextArea experience;

    @FXML
    private TextArea ProjectsTextArea;
    public UserDAO userDAO = new UserDAO();
    public User userProfile;
    private long id;

    @FXML
    private void initialize() {
        userImageView.setImage(fetchProfileImage());
        usernameLabel.setText(fetchUsername());
        userOnlineList.setItems(fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + fetchOnlineCount());
        userProfile.setId(id);
    }

    private Image fetchProfileImage() {
    
    
        if (userProfile != null) {
            String imagePath = userProfile.getPhoto(); // Assuming this is the file path stored in the database
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
        return userDAO.getUserById(id).getUsername();
    }

    private ListView<String> fetchOnlineList() {
        // Implement logic to fetch online users list
        return null; // Return the fetched online users list
    }

    private int fetchOnlineCount() {
        // Implement logic to fetch online count
        return 0; // Return the fetched online count
    }

    @FXML
    private void showProfile() {
        // Implement logic to show user profile
    }

    @FXML
    private void openChat() {
        // Implement logic to open chat
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
    private void closeApplication(MouseEvent event) {
        // Implement logic to close the application
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
        userpicture.setImage(image);
        return selectedFile.toURI().toString();
    }
    return null;
}

    @FXML
    private void editImage() {
        userProfile.setPhoto(browseForImage());
    }


    @FXML
    private void editName() {
        userProfile.setUsername(NameField.getText());
    }

    @FXML
    private void editBio() {
        userProfile.setBio(BioArea.getText());
    }

    @FXML
    private void editSkills() {
        userDAO.g
    }

    @FXML
    private void editExperience() {
        // Implement logic to update user experience
    }

    @FXML
    private void editProjects() {
        // Implement logic to update user projects
    }

    @FXML
    private void returnToMainProfile() {
        // Implement logic to return to main user profile
    }
}
