import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import java.io.File;
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
    private List userOnlineList;
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
    @FXML
    private void initialize{
        userImageView.setImage(Util.fetchProfileImage());
        usernameLabel.setText(Util.fetchUsername());
        userOnlineList.setItems(Util.fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + Util.fetchOnlineCount());}


    @FXML
    private void showProfile() {
        Util.showProfile();
    }

    @FXML
    private void openChat() {
        Util.openChat();
    }

    @FXML
    private void handleNotificationButton() {
        Util.handleNotificationButton();
    }

    @FXML
    private void handleSettingsButton() {
        Util.handleSettingsButton();
    }

    @FXML
    private void handleLogout() {
        Util.handleLogoutButton();
    }

    @FXML
    private void closeApplication(MouseEvent event) {
        Util.closeApplication();}
    @FXML
    private void browseForImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Company Logo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            userpicture.setImage(new javafx.scene.image.Image(selectedFile.toURI().toString()));
        }
    }

    @FXML
    private void editImage() {
        Util.updateCompanyLogo(userpicture.getImage());
     }
    @FXML
    private void browseForVideo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Video CV");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mkv")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            videoView.setMedia(new Media(selectedFile.toURI().toString()));
        }
    }

    @FXML
    private void editVideocv() {
        Util.updateVideoCV(videoView.getMedia());
    }

    @FXML
    private void editName() {
    util.UpdateName(NameField.getText());
    }

    @FXML
    private void editBio() {
    util.UpdateBio(BioArea.getText());
    }

    @FXML
    private void editskills() {
    util.UpdateServices(skillsTextarea.getText());
    }

    @FXML
    private void editexperience() {
    util.UpdateExperience(skillsTextarea.getText());
    }

    @FXML
    private void editProjects() {
    util.UpdateProjects(ProjectsTextArea.getText());
    }

    @FXML
    private void returnToMainProfile() {
        Util.returnToMainProfile();
    }
}
