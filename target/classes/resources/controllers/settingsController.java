import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class ChatController {
    @FXML
    private ImageView userImageView;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label onlineCountLabel;

    @FXML
    private ListView<String> userList;

    @FXML
    private void initialize() {
        userImageView.setImage(Util.fetchProfileImage());
        usernameLabel.setText(Util.fetchUsername());
        onlineCountLabel.setText("Online: " + Util.fetchOnlineCount());
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
    private void deleteProfile() {
    Util.switchtodeleteProfile();
    }

    @FXML
    private void security() {
    util.switchtoSecurity();
    }

    @FXML
    private void feedback() {
    util.switchtoFeedback();}
}
