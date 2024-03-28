import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class JobPostController {
    @FXML
    private ImageView userImageView;

    @FXML
    private ImageView closeButton;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label userOnlineCountLabel;
    @FXML
    private Label friendCountLabel;
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
    private Button handleLogoutButton;

    @FXML
    private TextField jobTitleField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField experienceField;
    @FXML
    private TextField locationField;
    @FXML
    private TextArea descriptionArea;

    @FXML
    private void initialize() {
        userImageView.setImage(Util.fetchProfileImage());
        usernameLabel.setText(Util.fetchUsername());
        userOnlineList.setItems(Util.fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + Util.fetchOnlineCount());
    }

    @FXML
    private void showProfile() {
        Util.showProfile();
    }

    @FXML
    private void openChat() {
        Util.openChat();
    }
    @FXML
    private void handlecloseButton() {
        Util.handlecloseButton();
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
    private void handleLogoutButton() {
        Util.handleLogoutButton();
    }
    @FXML
    private void closeApplication(MouseEvent event) {
        Util.closeApplication();}

    @FXML
    private void postJobOffer() {
        String jobTitle = jobTitleField.getText();
        String salary = salaryField.getText();
        String experience = experienceField.getText();
        String location = locationField.getText();
        String description = descriptionArea.getText();
        Util.postJobOffer(jobTitle, salary, experience, location, description);
    }
}
