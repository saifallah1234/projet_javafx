import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class CompanyProfileEditController {
    @FXML
    private ImageView userImageView;

    @FXML
    private ImageView closeButton;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label userOnlineCountLabel;
    @FXML
    private List userOnlineList;
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
    private ImageView companyLogo;

    @FXML
    private TextField companyNameField;

    @FXML
    private TextArea companyBioArea;

    @FXML
    private ListView<String> servicesListView;

    @FXML
    private ListView<String> teamListView;

    @FXML
    private ListView<String> companyProjectsListView;
    @FXML
    private void initialize {
        userImageView.setImage(Util.fetchProfileImage());
        usernameLabel.setText(Util.fetchUsername());
        userOnlineList.setItems(Util.fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + Util.fetchOnlineCount());}


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
    private void handleLogout() {
        Util.handleLogoutButton();
    }

    @FXML
    private void closeApplication(MouseEvent event) {
        Util.closeApplication();}
   @FXML
    private void browseForImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Company Logo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            companyLogo.setImage(new javafx.scene.image.Image(selectedFile.toURI().toString()));
        }
    }
    @FXML
    private void editImage() {
        Util.updateCompanyLogo(companyLogo.getImage());
    }
    @FXML
    private void editCompanyName() {
        util.UpdateName(companyNameField.getText());
    }


    @FXML
    private void editCompanyBio() {
        util.UpdateBio(companyBioArea.getText());
    }

    @FXML
    private void editServices() {
    util.UpdateServices(servicesListView.getItems());
    }
     @FXML
    private void editTeamMembers() {
    util.UpdateTeamMembers(teamListView.getItems());
    }
     @FXML
    private void editCompanyProjects() {
    util.UpdateProjects(companyProjectsListView.getItems());
    }
    @FXML
    private void returnToMainProfile() {
        Util.switchToCompanyProfile();
    }
}
