package FSB.pro.controllers;

import java.util.List;


import FSB.pro.DAO.UserDAO;
import FSB.pro.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


public class UserMainProfileController {
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
    User userProfile;
    UserDAO userDAO = new UserDAO();
    private long userId;

    public void initialize() {
        userProfile = userDAO.getUserById(userId);
        nameLabel.setText(userProfile.getFirstName() + " " + userProfile.getLastName();
        usernameLabel.setText("@" + userProfile.getUsername());
        bioLabel.setText(userProfile.getBio());
        skillsLabel.setText("Skills:" + userProfile.getSkills());
        projectsLabel.setText("Projects:\n" + userProfile.getProjects());
        experienceLabel.setText("Experience:\n" + userProfile.getExperience());
        this.userFeed = fetchUserFeed();
        feedListView.getItems().addAll(userFeed);
        friendCountLabel.setText("Friends: " + fetchFriendCount());
        userOnlineCountLabel.setText("Online: " + fetchUserOnlineCount());
        this.userFriendList = fetchFriendList();
        userFriendList.getItems().addAll(userFriendList);
        userImageView.setImage(fetchUserImage());
    }
    

    private List<String> fetchUserFeed() {
        // Implement the method to fetch user feed
    }

    private int fetchFriendCount() {
        // Implement the method to fetch friend count
    }

    private int fetchUserOnlineCount() {
        // Implement the method to fetch online user count
    }

    private List<String> fetchFriendList() {
        // Implement the method to fetch friend list
    }

    private Image fetchUserImage() {
        // Implement the method to fetch user image
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
        private void HandleEditButton() {
            // Code to switch to edit page
        }
    
        @FXML
        private void handleLikeButtonAction() {
            String selectedPost = feedListView.getSelectionModel().getSelectedItem();
            if (selectedPost != null) {
                // Code to like post
            }
        }
    
        @FXML
        private void Addpost() {
            // Code to add post
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
    
        @FXML
        private void showProfile() {
            // Code to switch to profile page
        }
    
        @FXML
        private void openChat() {
            // Code to switch to chat list page
        }
    
        @FXML
        private void Settings() {
            // Code to switch to settings page
        }
    
        @FXML
        private void logout() {
            // Code to logout
        }
    }
    

