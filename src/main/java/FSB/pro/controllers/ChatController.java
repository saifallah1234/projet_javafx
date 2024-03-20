package FSB.pro.controllers;    

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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class ChatController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox onlineUsersVbox;

    @FXML
    private ListView<String> userList;

    @FXML
    private HBox onlineUsersHbox;

    @FXML
    private Label onlineCountLabel;

    @FXML
    private ScrollPane chatScrollPane;

    @FXML
    private ListView<String> chatPane;

    @FXML
    private TextArea messageBox;

    @FXML
    private Button buttonSend;

    @FXML
    private Button recordBtn;

    @FXML
    private ImageView microphoneImageView;

    @FXML
    private ImageView userImageView;

    @FXML
    private Label usernameLabel;

    @FXML
    private ComboBox<String> statusComboBox;

    public void initialize() {
        // Initialize the chat pane
        ObservableList<String> chatMessages = FXCollections.observableArrayList();
        chatMessages.add("User1: Hello");
        chatMessages.add("User2: Hi");
        chatMessages.add("User1: How are you?");
        chatMessages.add("User2: I'm good, thanks!");
        chatPane.setItems(chatMessages);
    
        // Initialize the user list
        ObservableList<String> users = FXCollections.observableArrayList();
        users.add("User1");
        users.add("User2");
        users.add("User3");
        userList.setItems(users);
    
        // Set initial values for username and status
        usernameLabel.setText("Dominic Heal");
        statusComboBox.setItems(FXCollections.observableArrayList("Online", "Away", "Busy"));
        statusComboBox.getSelectionModel().selectFirst();
    
        // Set actions for buttons
        buttonSend.setOnAction(event -> sendButtonAction());
        recordBtn.setOnAction(event -> recordVoiceMessage());
    
        // Add listener to user list selection model
        userList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handleUserSelection();
        });
    }
    

    @FXML
    private void sendButtonAction() {
        String message = messageBox.getText();
        if (!message.isEmpty()) {
            String formattedMessage = "You: " + message;
            chatPane.getItems().add(formattedMessage);
            messageBox.clear();
        }
    }

    @FXML
    private void recordVoiceMessage() {
        // Code to handle recording voice message
        microphoneImageView.setImage(new ImageView("path/to/recording_image.png").getImage());
    }

    @FXML
    private void closeApplication() {
        // Code to close the application
    }
    
    @FXML
    private void handleUserSelection() {
        String selectedUser = userList.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            System.out.println("Selected User: " + selectedUser);
            // Perform actions based on the selected user
        }
    }
    
    @FXML
    private void sendMessage() {
        String selectedUser = userList.getSelectionModel().getSelectedItem();
        String message = messageBox.getText();
        if (selectedUser != null && !message.isEmpty()) {
            String formattedMessage = selectedUser + ": " + message;
            chatPane.getItems().add(formattedMessage);
            messageBox.clear();
        }
    }
    
    public void updateOnlineUserCount(int count) {
        onlineCountLabel.setText(String.valueOf(count));
    }
}
