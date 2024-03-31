package FSB.pro.controllers;    

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


import FSB.pro.DAO.CompanyDAO;
import FSB.pro.DAO.MessageDAO;
import FSB.pro.DAO.UserDAO;
import FSB.pro.models.Messages;
import FSB.pro.models.User;
import FSB.pro.utils.EntityDao;
import FSB.pro.utils.SceneSwitcher;
import FSB.pro.utils.UserFetchImage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ChatController {
    @FXML
    private ImageView userImageView;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label onlineCountLabel;

    @FXML
    private ListView userOnlineList;
    @FXML
    private Label userOnlineCountLabel;
    @FXML
    private ListView<String> chatPane;

    @FXML
    private TextArea messageBox;
    @FXML
    private Button sendButton;
    @FXML
    private Button closeButton;
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
    
    MessageDAO  messageDAO = new MessageDAO();
    EntityDao entityDao;
    private long userId;
    public void userId(long userId){
        this.userId=userId;
    }
    UserDAO userDAO = new UserDAO();
    CompanyDAO companyDAO = new CompanyDAO();
    public void testDAO(long userId){
        if (userDAO.findByIdtest(userId)) {
            entityDao = userDAO;
        }
        else if (companyDAO.getCompanyByIdTest(userId)) {
            entityDao = companyDAO;      
        }
    }

    @FXML
    private void initialize() {
        // Load user profile image (replace with actual path)
        userImageView.setImage(entityDao.fetchProfileImage(userId));

        // Set username (replace with actual username)
        usernameLabel.setText(entityDao.fetchUsername(userId));

        // Populate online user list (replace with actual data)
        ObservableList<User> onlineUsers = FXCollections.observableArrayList(fetchOnlineList());
        userOnlineList.setItems(onlineUsers);
        userOnlineCountLabel.setText("Online: " + onlineUsers.size());

        // Populate chat pane (replace with actual chat messages)
        ListView<Messages> chatPane = new ListView<>();

        chatPane.setItems(FXCollections.observableArrayList(fetchChat())); // Fix: Set an empty ObservableList as the items for the chatPane ListView
    }

    // Handle close application action

    private List<User> fetchOnlineList() {
        // Implement logic to fetch online users list
        return userDAO.getAllUsers().stream().filter(user -> user.isLogged()).collect(Collectors.toList()); // Return the fetched online users list
    }
    public List<Messages> fetchChat(){
         List<Messages> sentMessages = messageDAO.getMessagesSentByUser(userId);
        List<Messages> receivedMessages = messageDAO.getMessagesReceivedByUser(userId);

    // Combine sent and received messages into a single list
    List<Messages> allMessages = new ArrayList<>(sentMessages);
    allMessages.addAll(receivedMessages);

    // Sort the combined list by message timestamp (if applicable)
    Collections.sort(allMessages, Comparator.comparing(Messages::getSendDate));

    return allMessages;
    }


    @FXML
    private void closeApplication() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
    }

    // Handle show profile action
    @FXML
    private void showProfile() {
        if (entityDao instanceof UserDAO) {
            Stage currentStage = (Stage) showProfile.getScene().getWindow();
        UserMainProfileController profileController = SceneSwitcher.switchScene("UserMainProfileController.fxml", currentStage);
        
        }
        else{
            Stage currentStage = (Stage) showProfile.getScene().getWindow();
            UserCompanyProfile profileController = SceneSwitcher.switchScene("UserCompanyProfile.fxml", currentStage);
            profileController.userId(userId);
        }
        
    }

    // Handle open chat action
    @FXML
    private void openChat() {
        Stage currentStage = (Stage) openChat.getScene().getWindow();
        ChatController chatController = SceneSwitcher.switchScene("ChatView.fxml", currentStage);
        chatController.userId(userId);
    }

    // Handle sending a message
    @FXML
    private void sendButtonAction() {
        String message = messageBox.getText();
        
        chatMessages.add("You: " + message);
        messageBox.clear();
    }


}
