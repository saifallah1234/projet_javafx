import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.awt.*;

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
    private void initialize() {
        userImageView.setImage(Util.fetchProfileImage());
        usernameLabel.setText(Util.fetchUsername());
        userOnlineList.setItems(Util.fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + Util.fetchOnlineCount());
        chatPane.setItems(Util.fetchChat());
    }
    @FXML
    private void closeApplication() {
        Util.closeApplication();
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
    private void handleLogoutButton() {
        Util.handleLogoutButton();
    }



    @FXML
    private void sendButtonAction() {
        Util.sendMessage(messageBox.getText());
        messageBox.clear();
    }


}
