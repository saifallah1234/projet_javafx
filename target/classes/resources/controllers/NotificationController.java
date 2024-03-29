import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.control.ListCell;
import FSB.pro.models.User;
import FSB.pro.models.Notification;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NotificationController {

    @FXML
    private ListView<String> notificationListView;

    @FXML
    private ListView<User> userList;

    @FXML
    private ImageView userImageView;

    @FXML
    private Label usernameLabel;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private Label onlineCountLabel;

    @FXML
    private Button yourProfileButton;

    @FXML
    private Button notificationButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button logoutButton;


    private ObservableList<String> notifications; 

    @FXML
    private void initialize() {
        userImageView.setImage(Util.fetchProfileImage());
        usernameLabel.setText(Util.fetchUsername());
        userList.setItems(Util.fetchUserList());
        onlineCountLabel.setText("Online: " + Util.fetchOnlineCount());
        notificationListView.setItems(notifications);
        notificationListView.setCellFactory(listView -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    HBox hbox = new HBox();
                    hbox.setSpacing(10);
                    
                    // Fetch Notification object for action
                    Notification notification = getNotificationForAction(item); // Implement this method to fetch the Notification
                    
                    // Add profile photo ImageView
                    ImageView profilePhoto = new ImageView();
                    profilePhoto.setFitWidth(50);
                    profilePhoto.setFitHeight(50);
                    // Set the profile photo image from the user object
                    profilePhoto.setImage(new Image(notification.getFromUser().getPhoto()));
                    hbox.getChildren().add(profilePhoto);
                    
                    Label notificationLabel = new Label(item);
                    hbox.getChildren().add(notificationLabel);
                    
                    setGraphic(hbox);
                } else {
                    setGraphic(null);
                }
            }
        });
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

}
