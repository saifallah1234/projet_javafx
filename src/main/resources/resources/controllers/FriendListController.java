import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class FriendListController {
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
    private ListView<String> userList;


    @FXML
    private void initialize() {
        userImageView.setImage(Util.fetchProfileImage());
        usernameLabel.setText(Util.fetchUsername());
        userOnlineList.setItems(Util.fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + Util.fetchOnlineCount());
        userList.setItems(Util.fetchFriendList());
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
    private void closeApplication(MouseEvent event) {
        Util.closeApplication();}
}
