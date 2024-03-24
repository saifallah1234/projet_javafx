import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DashboardController {
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
    private Label firstName;

    @FXML
    private Label title1;

    @FXML
    private Label author1;

    @FXML
    private Label date1;

    @FXML
    private Label postText1;

    @FXML
    private Button addLike1;

    @FXML
    private Button addComment1;

    @FXML
    private Button editPost1;

    @FXML
    private Button deletePost1;

    @FXML
    private Button showCom1;

    @FXML
    private Label likeCount1;

    @FXML
    private Label comCount1;

    @FXML
    private Label errorOperation;

    @FXML
    private Label friendsCount;

    @FXML
    private Label messagesCount;

    @FXML
    private Label notCount;

    @FXML
    private Button addPost;
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
    private void addLike1() {
        Util.addLike1();
    }

    @FXML
    private void addComment1() {
        Util.addComment1();
    }

    @FXML
    private void editPost1() {
        Util.editPost1();
    }

    @FXML
    private void deletePost1() {
        Util.deletePost1();
    }

    @FXML
    private void showCom1() {
        Util.showCom1();
    }

    @FXML
    private void addPost() {
        Util.addPost();
    }


}
