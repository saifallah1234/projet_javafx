package FSB.pro.controllers;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import FSB.pro.DAO.CompanyDAO;
import FSB.pro.DAO.PostDAO;
import FSB.pro.DAO.UserDAO;
import FSB.pro.models.Company;
import FSB.pro.models.Post;
import FSB.pro.models.User;
import FSB.pro.utils.SceneSwitcher;
import FSB.pro.utils.UserFetchImage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompanyProfileController {

    @FXML
    private ImageView companyLogo;
    @FXML
    private Label companyNameLabel;
    @FXML
    private Label companyBioLabel;
    @FXML
    private Button followButton;
    @FXML
    private ListView<String> servicesListView;
    @FXML
    private ListView<String> teamListView;
    @FXML
    private ListView<String> companyProjectsListView;
    @FXML
    private Button likeButton;
    @FXML
    private Label likeCountLabel;
    @FXML
    private Button viewJobOpeningsButton;
    @FXML
    private ListView<Post> companyNewsListView;
    @FXML
    private ImageView userImageView;
    @FXML
    private Label usernameLabel;
    @FXML
    private ListView userOnlineList;
    @FXML
    private Label userOnlineCountLabel;

    @FXML
    private Button showProfile;
    @FXML
    private Button openChat;
    @FXML
    private Button handleLogout;
    @FXML
    private Button closeButton;
@FXML
private Label comment;
@FXML
private Label share;

    public PostDAO postDAO = new PostDAO();
    public UserDAO userDAO = new UserDAO();
    public CompanyDAO companyDAO = new CompanyDAO();
    public Company companyProfile;
    private long companyId;

    public void setCompanyId(long id){
        this.companyId = id;
    }

    public void initialize() {
        UserDAO userDAO = new UserDAO();
        companyProfile = companyDAO.getCompanyById(companyId); // Change 1 to the actual company ID
        userImageView.setImage(UserFetchImage.fetchProfileImage(companyDAO.getCompanyById(companyId).getLogo()));
        usernameLabel.setText(fetchUsername());
        userOnlineList.setItems(fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + fetchOnlineCount());

        companyNameLabel.setText(companyProfile.getName());
        companyBioLabel.setText(companyProfile.getDescription());
        servicesListView.getItems().addAll(companyProfile.getServices());
        teamListView.getItems().addAll(companyProfile.getTeamMembers());
        companyProjectsListView.getItems().addAll(companyProfile.getProjects());
        likeCountLabel.setText("Likes: " + companyProfile.getLikes());
        companyLogo.setImage(new Image(companyProfile.getLogo()));

        List<String> companyNews = companyProfile.getNews();
        companyNewsListView.getItems().addAll(companyNews);
    }


    private String fetchUsername() {
        // Implement logic to fetch username
        return companyProfile.getName(); // Return the fetched username
    }

    private ObservableList<String> fetchOnlineList() {
        // Implement logic to fetch online users list
         List<String> onlineUsers = userDAO.getAllUsers().stream().filter(x-> x.isLogged())
                .map(User::getUsername)
                .collect(Collectors.toList());
                return FXCollections.observableArrayList(onlineUsers); // Return the fetched online users list
    }

    private int fetchOnlineCount() {
        // Implement logic to fetch online count
        return (int) userDAO.getAllUsers().stream()
                .filter(User::isLogged) // Change User::isLogged to u -> u.isLogged()
                .count(); // Return the fetched online count
    }

    @FXML
    private void showProfile() {
        Stage currentStage = (Stage) showProfile.getScene().getWindow();
            UserCompanyProfile profileController = SceneSwitcher.switchScene("UserCompanyProfile.fxml", currentStage);
            profileController.userId(companyId);
        }
    }

    @FXML
    private void openChat() {
        Stage currentStage = (Stage) openChat.getScene().getWindow();
        ChatController chatController = SceneSwitcher.switchScene("ChatView.fxml", currentStage);
        chatController.userId(companyId);
    }

    @FXML
    private void handleNotificationButton() {
        // Implement logic to handle notifications
    }

    @FXML
    private void handleSettingsButton() {
        // Implement logic to handle settings
    }

    @FXML
    private void handleLogoutButton() {
        Stage currentStage = (Stage) handleLogout.getScene().getWindow();

        // Switch to the logout interface
        SceneSwitcher.switchScene("LoginView.fxml", currentStage);
    }

    @FXML
    private void closeApplication() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
    }

    @FXML
    private void handleFollowButtonAction() {
        // Implement logic to follow the company
    }

    @FXML
    private void handleLikeButtonAction() {
        // Implement logic to like the company
        int updatedLikes = companyProfile.getLikes() + 1;
        companyProfile.setLikes(updatedLikes);
        likeCountLabel.setText("Likes: " + updatedLikes);
        companyDAO.updateCompany(companyProfile);
    }

    @FXML
    private void handleViewJobOpeningsBtn() {
        // Implement logic to view job openings
    }

    @FXML
    private void handleLikeNewsButtonAction() {
        Post selectedPost = companyNewsListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            postDAO.incrementReaction(selectedPost.getId());
        }
    }

    @FXML
    private void handleCommentNewsButtonAction() {
        Post selectedPost = companyNewsListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Stage stage = (Stage) comment.getScene().getWindow();
        CommentController commentController = SceneSwitcher.switchScene("Comment.fxml", stage);
        commentController.postId(selectedPost.getId());
        }
    }

    @FXML
    private void handleShareNewsButtonAction() {
        Post selectedPost = companyNewsListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            // Implement logic to share news
        }
    }
}
