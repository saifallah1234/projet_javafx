package FSB.pro.controllers;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import FSB.pro.DAO.CommentDAO;
import FSB.pro.DAO.PostDAO;
import FSB.pro.DAO.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import FSB.pro.models.Comment;
import FSB.pro.models.User;
import FSB.pro.models.Post;
import javafx.scene.image.Image;

public class CommentController {

    @FXML
    private ImageView userImageView;

    @FXML
    private ImageView closeButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label userOnlineCountLabel;

    @FXML
    private Label title;

    @FXML
    private Label postText;

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
    private Button refreshButton;

    @FXML
    private TextField text;

    private CommentDAO commentDAO;

    private Long postId;
    private Long userId;
    public long postId(Long postId) {
        return postId;
    }

    public void initialize() {
        commentDAO = new CommentDAO();

        userImageView.setImage(fetchProfileImage());
        usernameLabel.setText(fetchUsername());
        userOnlineCountLabel.setText("Online: " + fetchOnlineCount());
        title.setText(fetchPostTitle());
        postText.setText(fetchPostText());
    }

    @FXML
    private void showProfile() {
        // Implement logic to show user profile
    }

    @FXML
    private void openChat() {
        // Implement logic to open chat
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
    private void handleLogout() {
        // Implement logic to handle logout
    }

    @FXML
    private void closeApplication() {
        // Implement logic to close application
    }

    @FXML
    private void refresh() {
        // Fetching fresh data and updating labels
        userOnlineCountLabel.setText("Online: " + fetchOnlineCount());
        // Fetching additional data for labels
        title.setText(fetchPostTitle());
        postText.setText(fetchPostText());
    }

    @FXML
    private void addComment() {
        String commentText = text.getText();
        if (!commentText.isEmpty()) {
            Comment comment = new Comment();
            comment.setText(commentText);
            comment.setPostId(postId); // Set the postId here

            commentDAO.addComment(comment);

            // Clear the text field after adding the comment
            text.clear();

            // Refresh the comments list
            refreshComments();
        }
    }

    private void refreshComments() {
        List<Comment> comments = commentDAO.getCommentsByPostId(postId); // Get comments for the post

    }

    private Image fetchProfileImage() {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(userId);
    
        if (user != null) {
            String imagePath = user.getPhoto(); // Assuming this is the file path stored in the database
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
        UserDAO user = new UserDAO();
        return user.getUserById(userId).getUsername(); // Fetch username by userId
    }

    private int fetchOnlineCount() {
        List<User> Users = new UserDAO().getAllUsers(); // Fetch all online users
        List<User> onlineUsers = new ArrayList<>();
        for (User user : Users) {
            if (user.isLogged()) {
                onlineUsers.add(user);
            }
        }

        return onlineUsers.size(); // Return the fetched online count
    }

    private String fetchPostTitle() {
        PostDAO postDAO = new PostDAO();
        return postDAO.getPostById(postId).getTitle(); // Fetch post title by postId

         
    }

    private String fetchPostText() {
        PostDAO postDAO = new PostDAO();
        return postDAO.getPostById(postId).getText(); // Fetch post text by postId
}
}
