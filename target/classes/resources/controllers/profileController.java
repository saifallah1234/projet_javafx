package com.example.prjlink;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import java.util.List;

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
    private List userOnlineList;

    @FXML
    private Label userOnlineCountLabel;

    @FXML
    private Button viewFriendListButton;

    @FXML
    private ListView<String> userFriendList;

    public void initialize() {
        userImageView.setImage(Util.fetchProfileImage());
        usernameLabel.setText(Util.fetchUsername());
        userOnlineList.setItems(Util.fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + Util.fetchOnlineCount());
        nameLabel.setText(userProfile.getName());
        usernameLabel.setText("@" + userProfile.getUsername());
        bioLabel.setText(userProfile.getBio());
        skillsLabel.setText("Skills:" + userProfile.getSkills());
        projectsLabel.setText("Projects:\n" + userProfile.getProjects());
        experienceLabel.setText("Experience:\n" + userProfile.getExperience());
        List<String> userFeed = Util.fetchUserFeed();
        feedListView.getItems().addAll(userFeed);
        friendCountLabel.setText("Friends: " + Util.fetchFriendCount());
        userOnlineCountLabel.setText("Online: " + Util.fetchUserOnlineCount());
        List<String> userFriendList = Util.fetchFriendList();
        userFriendList.getItems().addAll(userFriendList);
        userImageView.setImage(Util.fetchUserImage());
    }

    @FXML
    private void handleViewCVVideoButtonAction() {
        Util.playCVVideo();
    }

    @FXML
    private void handleGeneratePDFButtonAction() {
        Util.generatePDF();
    }

    @FXML
    private void handleViewFriendListButtonAction() {
        Util.switchToFriendListPage();
    }

    @FXML
    private void handleFriendRequestButtonAction() {
        Util.sendFriendRequest();
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
        Util.closeApplication();}

    @FXML
    private void handleLikeButtonAction() {
        String selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Util.likePost(selectedPost);
        }
    }

    @FXML
    private void handleCommentButtonAction() {
        String selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Util.commentOnPost(selectedPost);
        }
    }

    @FXML
    private void handleRepostButtonAction() {
        String selectedPost = feedListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Util.repostPost(selectedPost);
        }
    }


}
