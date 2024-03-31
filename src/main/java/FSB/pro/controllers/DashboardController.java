package FSB.pro.controllers;


import java.util.List;

import java.util.stream.Collectors;

import FSB.pro.DAO.CompanyDAO;
import FSB.pro.DAO.PostDAO;
import FSB.pro.DAO.UserDAO;
import FSB.pro.models.Post;
import FSB.pro.models.User;
import FSB.pro.utils.DaoTester;
import FSB.pro.utils.EntityDao;
import FSB.pro.utils.SceneSwitcher;
import FSB.pro.utils.UserFetchImage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

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
    private ListView<User> userOnlineList;

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
    private ListView<Post> feedListView;


    @FXML
    private Button viewFriendListButton;

    public UserDAO userDAO = new UserDAO();
    public PostDAO postDAO = new PostDAO();
    private long iduser;
    public void iduser(long id){
        this.iduser=id;
    }
    EntityDao entityDao = DaoTester.tetst(iduser);
    @FXML
    private void initialize() {
        // Initialize user information (Replace with your logic)
        userImageView.setImage(entityDao.fetchProfileImage(iduser));
        usernameLabel.setText(entityDao.fetchUsername(iduser);
        ObservableList<User> observableOnlineList = FXCollections.observableArrayList(fetchOnlineList());
        userOnlineList.setItems(observableOnlineList);
        userOnlineCountLabel.setText("Online: " + fetchOnlineCount());
        List<Post> Feed = fetchFeed();

       
    }
    private List<Post> fetchFeed() {
        // Your implementation here
    }
    @FXML
private void Addpost() {
    // Implement logic to add a new post
    errorOperation.setText("Add Post operation not available in this demo.");
}



    private String fetchUsername() {
        // Implement logic to fetch username
        return userDAO.getUserById(iduser).getUsername(); // Return the fetched username
    }

    private List<User> fetchOnlineList() {
        // Implement logic to fetch online users list
        return userDAO.getAllUsers().stream().filter(user -> user.isLogged()).collect(Collectors.toList()); // Return the fetched online users list
    }

    private int fetchOnlineCount() {
        // Implement logic to fetch online count
        return (int)userDAO.getAllUsers().stream().filter(user -> user.isLogged()).count(); // Return the fetched online count
    }

    @FXML
    private void showProfile() {
       if (entityDao instanceof UserDAO) {
            Stage currentStage = (Stage) showProfile.getScene().getWindow();
        UserMainProfileController profileController = SceneSwitcher.switchScene("UserMainProfileController.fxml", currentStage);
        
        }
        else{
            Stage currentStage = (Stage) showProfile.getScene().getWindow();
            UserCompanyProfile profileController = SceneSwitcher.switchScene("UserCompanyProfile.fxml", currentStage);
            profileController.userId(iduser);
        }
    }
    @FXML
    private void showFriendrequest() {
       
    }


    @FXML
    private void openChat() {
        Stage currentStage = (Stage) openChat.getScene().getWindow();
        ChatController chatController = SceneSwitcher.switchScene("ChatView.fxml", currentStage);
        chatController.userId(iduser);
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
        Stage currentStage = (Stage) handleLogout.getScene().getWindow();

        // Switch to the logout interface
        SceneSwitcher.switchScene("LoginView.fxml", currentStage);
    }

    @FXML
    private void closeApplication(MouseEvent event) {
        // Implement logic to close the application
    }

    @FXML
    private void addLike1() {
        // Implement logic to add like to post 1
        int likes = Integer.parseInt(likeCount1.getText().replace("Likes: ", ""));
        likes++;
        likeCount1.setText("Likes: " + likes);
    }

    @FXML
    private void addComment1() {
        // Implement logic to add comment to post 1
        int comments = Integer.parseInt(comCount1.getText().replace("Comments: ", ""));
        comments++;
        comCount1.setText("Comments: " + comments);
    }

    @FXML
    private void editPost1() {
        // Implement logic to edit post 1
        errorOperation.setText("Edit operation not available in this demo.");
    }

    @FXML
    private void deletePost1() {
        // Implement logic to delete post 1
        errorOperation.setText("Delete operation not available in this demo.");
    }

    @FXML
    private void showCom1() {
        // Implement logic to show comments of post 1
        errorOperation.setText("Show Comments operation not available in this demo.");
    }

    @FXML
    private void addPost() {
        // Implement logic to add a new post
        errorOperation.setText("Add Post operation not available in this demo.");
    }
    
}
