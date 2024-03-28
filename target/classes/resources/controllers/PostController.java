import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;

import javax.swing.text.html.ListView;
import java.io.File;

public class PostController {
    @FXML
    private ImageView userImageView;

    @FXML
    private Label usernameLabel;


    @FXML
    private TextField text;

    @FXML
    private ListView userList;

    @FXML
    private MediaView videoView;

    private FileChooser fileChooser = new FileChooser();
    @FXML
    private ListView userOnlineList;
    @FXML
    private Label userOnlineCountLabel;


    @FXML
    private void initialize() {
        userImageView.setImage(Util.fetchProfileImage());
        usernameLabel.setText(Util.fetchUsername());
        userOnlineList.setItems(Util.fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + Util.fetchOnlineCount());
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
    private void closeApplication(MouseEvent event) {
        Util.closeApplication();
    }
    @FXML
    private void browse() {
        fileChooser.setTitle("Select a file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
    }
    @FXML
    private void post() {
        if (fileChooser.getSelectedFile() != null && !text.getText().isEmpty()) {
            Util.post(text.getText(), fileChooser.getSelectedFile());
        } else if (fileChooser.getSelectedFile() == null && !text.getText().isEmpty()) {
            Util.post(text.getText());
        } else if (fileChooser.getSelectedFile() != null && text.getText().isEmpty()) {
            Util.post(fileChooser.getSelectedFile());
        }
    }
}
