package FSB.pro.controllers;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import FSB.pro.DAO.FriendshipDAO;
import FSB.pro.DAO.UserDAO;
import FSB.pro.models.User;
import FSB.pro.utils.SceneSwitcher;
import FSB.pro.utils.UserFetchImage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class FriendListController {
    
    @FXML
    private ImageView userImageView;

    @FXML
    private ImageView closeButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label userOnlineCountLabel;

    @FXML
    private ListView<String> userOnlineList;

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
    private ListView<String> userList;
    public FriendshipDAO friendshipDAO = new FriendshipDAO();
     
    public UserDAO userDAO = new UserDAO();
    private long userId;

    public void userId(long userId){
        this.userId=userId;
    }
    public User user = userDAO.getUserById(userId);

    @FXML
    private void initialize() {
        User user = userDAO.getUserById(userId);
        userImageView.setImage(UserFetchImage.fetchProfileImage(userDAO.getUserById(userId).getPhoto()));
        usernameLabel.setText(fetchUsername());
        ObservableList<String> onlineUsernames = fetchOnlineList();
        userOnlineList.setItems(onlineUsernames);
        userOnlineCountLabel.setText("Online: " + fetchOnlineCount());
        userList.setItems(fetchFriendList());
        user.setId(userId);
    }



    private String fetchUsername() {
         // Implement logic to fetch username
        return userDAO.getUserById(userId).getUsername(); // Return the fetched username
    }

    private ObservableList<String> fetchOnlineList() {
    List<User> onlineUsers = userDAO.getAllUsers().stream().filter(User::isLogged).collect(Collectors.toList());
    ObservableList<String> onlineUsernames = FXCollections.observableArrayList();

    for (User user : onlineUsers) {
        onlineUsernames.add(user.getUsername());
    }

    return onlineUsernames;
}
    

    private int fetchOnlineCount() {
        // Implement logic to fetch online count
         
        List<User> UsersOnline = userDAO.getAllUsers().stream().filter(User::isLogged).collect(Collectors.toList());
        return UsersOnline.size(); // Return the fetched online count
    }

    private ObservableList<String> fetchFriendList() {
        // Implement logic to fetch user's friend list
        List<String> onlineUsers = userDAO.getAllUsers().stream().filter(x-> x.isLogged())
        .map(User::getUsername)
        .collect(Collectors.toList());
        return FXCollections.observableArrayList(onlineUsers); // Convert it to ObservableList and return
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
    private void closeApplication(MouseEvent event) {
        Object source = event.getSource();
        if (source instanceof Button) {
            Button clickedButton = (Button) source;
            Stage stage = (Stage) clickedButton.getScene().getWindow();
            stage.close();
        }
    
    }
}
