package FSB.pro.controllers;

import java.util.List;

import FSB.pro.DAO.FriendshipDAO;
import FSB.pro.DAO.PostDAO;
import FSB.pro.DAO.UserDAO;
import FSB.pro.models.Post;
import FSB.pro.models.User;
import FSB.pro.utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserMainProfileController {
    @FXML
    private ImageView profileImage;

    @FXML
    private Label nameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label userBioLabel; // Updated to match fx:id in FXML

    @FXML
    private Label skillsLabel;

    @FXML
    private Label projectsLabel;

    @FXML
    private Label experienceLabel;

    @FXML
    private ListView<Post> feedListView;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private Button viewCVVideoButton;

    @FXML
    private Button generatePDFButton;

    @FXML
    private Button friendRequestButton;

    @FXML
    private ListView<User> userList;

    @FXML
    private Label onlineCountLabel;

    @FXML
    private VBox onlineUsersVBox;

    @FXML
    private ListView<User> userFriendList;

    @FXML
    private Button editButton;

    @FXML
    private Button openChatButton;

    @FXML
    private Button logoutButton;

    private long userId;

    User userProfile;
    UserDAO userDAO = new UserDAO();
    PostDAO postDAO = new PostDAO();
    FriendshipDAO friendshipDAO = new FriendshipDAO();

    public UserMainProfileController(long userId) {
        this.userId = userId;
    }

    public void initialize() {
        System.err.println("User ID in initialize : " + userId);
        userProfile = userDAO.getUserById(userId);
        if (userProfile != null) {
            System.out.println("User profile retrieved successfully: " + userProfile.getUsername());
            nameLabel.setText(userProfile.getFirstName() + " " + userProfile.getLastName());
            usernameLabel.setText("@" + userProfile.getUsername());
            //skillsLabel.setText("Skills: " + userProfile.getSkills());
            //experienceLabel.setText("Experience:\n" + userProfile.getExperiences());

            //feedListView.getItems().addAll(fetchUserFeed());
            onlineCountLabel.setText("Online: " + fetchUserOnlineCount());
            //userFriendList.getItems().addAll(fetchFriendList());
            //profileImage.setImage(fetchUserImage());
        } else {
            System.err.println("User profile is null.");
        }
    }

    private List<Post> fetchUserFeed() {
        return postDAO.getPostBySenderId(userId);
    }

    private int fetchUserOnlineCount() {
        return (int) userDAO.getAllUsers().stream().filter(User::isLogged).count();
    }

    private List<User> fetchFriendList() {
        return friendshipDAO.getFriendsByUserId(userId);
    }

    private Image fetchUserImage() {
        return new Image(userDAO.getUserById(userId).getPhoto());
    }

    @FXML
    private void viewCVVideo() {
        // Code to play CV video
    }

    @FXML
    private void generatePDF() {
        // Code to generate PDF
        System.out.println("Generate PDF button clicked!");
        // Call your PDF generation logic here
    }

    @FXML
    private void handleViewFriendListButtonAction() {
        // Code to switch to friend list page
    }

    @FXML
    private void handleEditButton() {
        Stage currentStage = (Stage) editButton.getScene().getWindow();
        EditUserProfileController editUserProfileController = SceneSwitcher.switchScene("edituserprofile.fxml", currentStage);
        editUserProfileController.userid(userId);
    }

    @FXML
    private void handleLikeButtonAction() {
        Post selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            postDAO.incrementReaction(selectedPost.getId());
        }
    }

    @FXML
    private void handleAddPostButtonAction() {
        Stage currentStage = (Stage) editButton.getScene().getWindow();
        EditUserProfileController editUserProfileController = SceneSwitcher.switchScene("edituserprofile.fxml", currentStage);
        editUserProfileController.userid(userId);
    }

    @FXML
    private void handleCommentButtonAction() {
        Post selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            CommentController commentController = SceneSwitcher.switchScene("Comment.fxml", (Stage) editButton.getScene().getWindow());
            commentController.postId(selectedPost.getId());
        }
    }

    @FXML
    private void handleRepostButtonAction() {
        Post selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            // Code to repost post
        }
    }

    @FXML
    private void openChat() {
        Stage currentStage = (Stage) openChatButton.getScene().getWindow();
        ChatController chatController = SceneSwitcher.switchScene("ChatView.fxml", currentStage);
        chatController.userId(userId);
    }

    @FXML
    private void HandleLogoutButton() {
        Stage currentStage = (Stage) logoutButton.getScene().getWindow();

        // Switch to the logout interface
        SceneSwitcher.switchScene("LoginView.fxml", currentStage);
    }

    @FXML
    private void showProfile() {
        // Show the user profile
    }

    @FXML
    private void HandleSettingsButton() {
        // Show the user settings
    }

    @FXML
    public void HandleNotificationButton() {
        // Notification button action
    }
}
