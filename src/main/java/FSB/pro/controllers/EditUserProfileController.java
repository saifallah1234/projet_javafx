package FSB.pro.controllers;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import FSB.pro.DAO.UserDAO;
import FSB.pro.models.User;
import FSB.pro.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.media.MediaView;

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
    private Button editImageButton;
    

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
    public void userid(long id){
        this.id=id;
    }

    @FXML
    private void initialize() {
        userProfile = userDAO.getUserById(id);
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

    private ObservableList<String> fetchOnlineList() {
    List<String> onlineUsers = userDAO.getAllUsers().stream()
        .filter(User::isLogged)
        .map(User::getUsername)
        .collect(Collectors.toList());

    // Convert the List<String> to ObservableList<String>
    ObservableList<String> onlineUsersObservableList = FXCollections.observableArrayList(onlineUsers);

    return onlineUsersObservableList;
}



    private int fetchOnlineCount() {
        // Implement logic to fetch online count
        return userDAO.getAllUsers().stream().filter(x ->x.isLogged()).collect(Collectors.toList()).size(); // Return the fetched online count
    }

    @FXML
    private void showProfile() {
        Stage currentStage = (Stage) showProfile.getScene().getWindow();
        UserMainProfileController profileController = SceneSwitcher.switchScene("UserMainProfileController.fxml", currentStage);
        profileController.userId(id);
    }

    @FXML
    private void openChat() {
        Stage currentStage = (Stage) openChat.getScene().getWindow();
        ChatController chatController = SceneSwitcher.switchScene("ChatView.fxml", currentStage);
        chatController.userId(id);
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
        Stage currentStage = (Stage) handleLogout.getScene().getWindow();

        // Switch to the logout interface
        SceneSwitcher.switchScene("LoginView.fxml", currentStage);
    }

    @FXML
    private void closeApplication(MouseEvent event) {
        Object source = event.getSource();
        if (source instanceof Button) {
            Button clickedButton = (Button) source;
            Stage stage = (Stage) clickedButton.getScene().getWindow();
            stage.close();
        }
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
