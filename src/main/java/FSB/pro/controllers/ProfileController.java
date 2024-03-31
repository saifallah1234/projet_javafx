package FSB.pro.controllers;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import FSB.pro.DAO.FriendshipDAO;
import FSB.pro.DAO.PostDAO;
import FSB.pro.DAO.UserDAO;
import FSB.pro.models.Post;
import FSB.pro.models.User;
import FSB.pro.utils.SceneSwitcher;
import FSB.pro.utils.UserFetchImage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ProfileController {
    @FXML
    private ImageView profileImage;

    @FXML
    private Label nameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label bioLabel;

    @FXML
    private Label skillsLabel;

    @FXML
    private Label projectsLabel;

    @FXML
    private Label experienceLabel;

    @FXML
    private ListView<Post> feedListView;

    @FXML
    private TextField postTextField;

    @FXML
    private ImageView userImageView;

    @FXML
    private Button viewCVVideoButton;

    @FXML
    private Button generatePDFButton;

    @FXML
    private Button friendRequestButton;

    @FXML
    private ListView<String> userOnlineList;

    @FXML
    private Label userOnlineCountLabel;

    @FXML
    private Button viewFriendListButton;

    @FXML
    private ListView<User> userFriendList;
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
    

    public UserDAO userDAO = new UserDAO();
    public FriendshipDAO friendshipDAO = new FriendshipDAO();
    public PostDAO postDAO = new PostDAO();
    private long userId;

    private long id(long userId) {
        return this.userId = userId;
    }
    public ProfileController(long userId){
        this.userId = userId;
    }
    User userProfile = userDAO.getUserById(userId);

    public void initialize() {
        userImageView.setImage(UserFetchImage.fetchProfileImage(userDAO.getUserById(userId).getPhoto()));
        usernameLabel.setText(fetchUsername());
        userOnlineList.getItems().addAll(fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + fetchOnlineCount());
        nameLabel.setText(userProfile.getFirstName());
        usernameLabel.setText("@" + userProfile.getUsername());
        bioLabel.setText(userProfile.getBio());
        skillsLabel.setText("Skills:" + userProfile);
        projectsLabel.setText("Projects:\n" + userProfile.getProjects());
        experienceLabel.setText("Experience:\n" + userProfile.getExperiences());
        ObservableList<Post> observableFeedList = FXCollections.observableArrayList(fetchUserFeed());
        feedListView.setItems(observableFeedList);
        ObservableList<User> observableFriendList = FXCollections.observableArrayList(fetchFriendList());
        userFriendList.setItems(observableFriendList);
        userImageView.setImage(fetchUserImage());
    }

    private String fetchUsername() {
        // Code to fetch username
        return userDAO.getUserById(userId).getUsername();
    }

    private List<String> fetchOnlineList() {
        // Code to fetch online list
        List<String> onlineUsers = userDAO.getAllUsers().stream()
                .filter(User::isLogged)
                .map(User::getUsername)
                .collect(Collectors.toList());
        return onlineUsers;
    }

    private int fetchOnlineCount() {
        return (int) userDAO.getAllUsers().stream().filter(User::isLogged).count();
    }

    private List<Post> fetchUserFeed() {
        return postDAO.getPostBySenderId(userId);
    }

    private int fetchFriendCount() {
        return userDAO.getUserById(userId).getFriends().size();
    }

    private List<User> fetchFriendList() {
       return friendshipDAO.getFriendsByUserId(userId);
    }

    private Image fetchUserImage() {
        return new Image(userDAO.getUserById(userId).getPhoto());
    }

    @FXML
    private void handleViewCVVideoButtonAction() {
        // Code to play CV video
    }

    @FXML
    private void handleGeneratePDFButtonAction() {
        // Code to generate PDF
    }

    @FXML
    private void handleViewFriendListButtonAction() {
        // Code to switch to friend list page
    }

    @FXML
    private void handleFriendRequestButtonAction() {
        // Code to send friend request
    }

    @FXML
    private void showProfile() {
         Stage currentStage = (Stage) showProfile.getScene().getWindow();
        UserMainProfileController profileController = SceneSwitcher.switchScene("UserMainProfileController.fxml", currentStage);
        profileController.userId(userId);
    }

    @FXML
    private void openChat() {
        Stage currentStage = (Stage) openChat.getScene().getWindow();
        ChatController chatController = SceneSwitcher.switchScene("ChatView.fxml", currentStage);
        chatController.userId(userId);
    }

    @FXML
    private void handleNotificationButton() {
        // Code to handle notification button
    }

    @FXML
    private void handleSettingsButton() {
        // Code to handle settings button
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
    private void handleLikeButtonAction() {
        Post selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            // Code to like post
        }
    }

    @FXML
    private void handleCommentButtonAction() {
        Post selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            // Code to comment on post
        }
    }

    @FXML
    private void handleRepostButtonAction() {
        Post selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            // Code to repost post
        }
    }
}

