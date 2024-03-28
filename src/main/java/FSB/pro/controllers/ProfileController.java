package FSB.pro.controllers;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import FSB.pro.DAO.UserDAO;
import FSB.pro.models.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


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
    private ListView<String> feedListView;

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
    private ListView<String> userFriendList;

    public UserDAO userDAO = new UserDAO();
    private long userId;

    private long id(long userId) {
        return this.userId = userId;
    }
    User userProfile = userDAO.getUserById(userId);

    public void initialize() {
        userImageView.setImage(fetchProfileImage());
        usernameLabel.setText(fetchUsername());
        userOnlineList.getItems().addAll(fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + fetchOnlineCount());
        nameLabel.setText(userProfile.getFirstName());
        usernameLabel.setText("@" + userProfile.getUsername());
        bioLabel.setText(userProfile.getBio());
        skillsLabel.setText("Skills:" + userProfile);
        projectsLabel.setText("Projects:\n" + userProfile.getProjects());
        experienceLabel.setText("Experience:\n" + userProfile.getExperience());
        List<String> userFeed = fetchUserFeed();
        feedListView.getItems().addAll(userFeed);
        List<String> userFriendList = fetchFriendList();
        userFriendList.getItems().addAll(userFriendList);
        userImageView.setImage(fetchUserImage());
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
        // Code to fetch online count
    }

    private List<String> fetchUserFeed() {
        // Code to fetch user feed
    }

    private int fetchFriendCount() {
        // Code to fetch friend count
    }

    private List<String> fetchFriendList() {
        // Code to fetch friend list
    }

    private Image fetchUserImage() {
        // Code to fetch user image
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
        // Code to show profile
    }

    @FXML
    private void openChat() {
        // Code to open chat
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
        // Code to handle logout button
    }

    @FXML
    private void closeApplication(MouseEvent event) {
        // Code to close application
    }

    @FXML
    private void handleLikeButtonAction() {
        String selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            // Code to like post
        }
    }

    @FXML
    private void handleCommentButtonAction() {
        String selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            // Code to comment on post
        }
    }

    @FXML
    private void handleRepostButtonAction() {
        String selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            // Code to repost post
        }
    }
}

