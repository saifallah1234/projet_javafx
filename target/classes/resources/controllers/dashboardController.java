import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;

public class dashboardController {
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
    private ListView<Post> feedListView;

    @FXML
    private void initialize() {
        userImageView.setImage(Util.fetchProfileImage());
        usernameLabel.setText(Util.fetchUsername());
        userOnlineList.setItems(Util.fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + Util.fetchOnlineCount());
        feedListView.setItems(Util.fetchFeed());
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
    private void addLike() {
        Post selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Util.addLike(selectedPost);
        }
    }

    @FXML
    private void addComment() {
        Post selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Util.addComment(selectedPost);
        }
    }

    @FXML
    private void handleRepostButtonAction() {
        Post selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Util.repost(selectedPost);
        }
    }

    @FXML
    private void addPost() {
        Util.addPost();
    }
}
