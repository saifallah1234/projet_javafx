package FSB.pro.controllers;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

import FSB.pro.DAO.PostDAO;
import FSB.pro.DAO.UserDAO;
import FSB.pro.models.Post;
import FSB.pro.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.stage.FileChooser;
import javafx.scene.image.Image;


public class PostController {
    @FXML
    private ImageView userImageView;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField text;

    @FXML
    private ListView<String> userOnlineList;

    @FXML
    private Label userOnlineCountLabel;


    public UserDAO userDAO = new UserDAO();
    private FileChooser fileChooser = new FileChooser();
    private long userId;
    private long postid;
    public void userId(long userId){
        this.userId=userId;
    }
public PostDAO postDAO = new PostDAO();

    @FXML
    private void initialize() {
        // Initialize user information
        userImageView.setImage(fetchProfileImage());
        usernameLabel.setText(fetchUsername());
        userOnlineList.getItems().addAll(fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + fetchOnlineCount());
    }
    private long senderId(long id){
        return this.userId=id;
    }
    private long userid(long userId){
        return this.userId=userId;
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
        // Implement logic to fetch username
        return userDAO.getUserById(userId).getUsername(); // Return the fetched username
    }

    private List<String> fetchOnlineList() {
        // Implement logic to fetch online users list
        List<String> onlineUsers = userDAO.getAllUsers().stream()
                .filter(User::isLogged)
                .map(User::getUsername)
                .collect(Collectors.toList());
        return onlineUsers; // Return the fetched online users list
    }

    private int fetchOnlineCount() {
        List <User> Users = userDAO.getAllUsers().stream().filter(User::isLogged).collect(Collectors.toList());
         // Fetch all online users
        return Users.size(); // Return the fetched online count
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
    private void handleLogoutButton() {
        // Implement logic to handle logout
    }

    @FXML
    private void closeApplication(MouseEvent event) {
        // Implement logic to close the application
    }

    @FXML
    private void browse() {
        fileChooser.setTitle("Select a file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        // Handle selected file, if needed
    }

    @FXML
private void post() {
    if (fileChooser.showOpenDialog(null) != null || !text.getText().isEmpty()) {
        // Post with both text and file
        File selectedFile = fileChooser.showOpenDialog(null);
        String postText = "";
        if (postid != 0) {
            postText = text.getText();
            
        }
        else
        {
            postText = text.getText();
        }
        
        // Create a new Post object
        Post newPost = new Post();
        newPost.setTitle("Title");  // You can set the title here
        newPost.setText(postText);
        newPost.setSendDate(LocalDate.now());
        newPost.setSendTime(LocalDateTime.now());
        newPost.setSenderId(userId);  // Set the senderId here, replace '1L' with the actual sender ID

        // Add the post to the database using the PostDAO
        PostDAO postDAO = new PostDAO();
        postDAO.addPost(newPost);

        // Clear the text field after posting
        text.clear();

        // Optional: Display a message or update UI after posting
        System.out.println("Post added successfully.");
    } else {
        // Handle case where neither file nor text is provided
        System.out.println("Please enter text or select a file to post.");
    }
}


    public void postId(Long id) {
       this.postid=id;
    }

}
