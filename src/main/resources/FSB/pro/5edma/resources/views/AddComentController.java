import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class AddCommentController {
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
    private TextField text;
    @FXML
    private Label title;
    @FXML
    private Label postText;
    @FXML
    private Button refreshButton;

    @FXML
    private void initialize() {
              userImageView.setImage(Util.fetchProfileImage());
        usernameLabel.setText(Util.fetchUsername());
        userOnlineList.setItems(Util.fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + Util.fetchOnlineCount());
        title.setText(Util.fetchPostTitle());
        postText.setText(Util.fetchPostText());
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
    private void handleLogout() {
        Util.handleLogoutButton();
    }

    @FXML
    private void closeApplication(MouseEvent event) {
        Util.closeApplication();
    }

    @FXML
    private void refresh() {
        // Fetching fresh data and updating labels
        friendCountLabel.setText("Friends: " + Util.fetchFriendCount());
        userOnlineCountLabel.setText("Online: " + Util.fetchOnlineCount());
        // Fetching additional data for labels
        title.setText(Util.fetchPostTitle());
        postText.setText(Util.fetchPostText());
    }

    @FXML
    private void addComment() {
        String commentText = text.getText();
        Util.addComment(commentText);
    }
}
