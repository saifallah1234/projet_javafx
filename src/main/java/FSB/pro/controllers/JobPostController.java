package FSB.pro.controllers;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import FSB.pro.DAO.CompanyDAO;
import FSB.pro.DAO.JobOfferDAO;
import FSB.pro.DAO.UserDAO;
import FSB.pro.models.JobOffer;
import FSB.pro.models.User;
import FSB.pro.utils.SceneSwitcher;
import FSB.pro.utils.UserFetchImage;

public class JobPostController {

    @FXML
    private ImageView userImageView;

    @FXML
    private ImageView closeButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label userOnlineCountLabel;

    @FXML
    private Label friendCountLabel;

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
    private Button handleLogoutButton;

    @FXML
    private TextField jobTitleField;

    @FXML
    private TextField salaryField;

    @FXML
    private TextField experienceField;

    @FXML
    private TextField locationField;

    @FXML
    private TextArea descriptionArea;

    private JobOfferDAO jobOfferDAO;
    public CompanyDAO companyDAO = new CompanyDAO();
    UserDAO userDAO = new UserDAO();
    public long companyId;
    public void companyId(long id){
        this.companyId=id;
    }


    @FXML
    private void initialize() {
        userImageView.setImage(UserFetchImage.fetchProfileImage(companyDAO.getCompanyById(companyId).getLogo()));
        usernameLabel.setText(fetchUsername());
        userOnlineList.setItems(fetchFriendList());
        userOnlineCountLabel.setText("Online: " + fetchOnlineCount());

        jobOfferDAO = new JobOfferDAO();
        JobOffer jobOffer = new JobOffer();
    }
    }

    private String fetchUsername() {
        // Implement logic to fetch username
        return userDAO.getUserById(companyId).getUsername(); // Return the fetched username
    }

    private ObservableList<String> fetchFriendList() {
        // Implement logic to fetch user's friend list
        List<String> onlineUsers = userDAO.getAllUsers().stream().filter(x-> x.isLogged())
        .map(User::getUsername)
        .collect(Collectors.toList());
        return FXCollections.observableArrayList(onlineUsers); // Convert it to ObservableList and return
    }

    private int fetchOnlineCount() {
        // Implement logic to fetch online count
        return (int)userDAO.getAllUsers().stream().filter(x ->x.isLogged()).count(); // Return the fetched online count
    }

    @FXML
    private void showProfile() {
       Stage currentStage = (Stage) showProfile.getScene().getWindow();
            UserCompanyProfile profileController = SceneSwitcher.switchScene("UserCompanyProfile.fxml", currentStage);
            profileController.userId(companyId);
    }

    @FXML
    private void openChat() {
        Stage currentStage = (Stage) openChat.getScene().getWindow();
        ChatController chatController = SceneSwitcher.switchScene("ChatView.fxml", currentStage);
        chatController.userId(companyId);
    }

    @FXML
    private void handlecloseButton() {
        // Implement logic to handle close button
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

    @FXML
    private void postJobOffer() {
        String jobTitle = jobTitleField.getText();
        String salary = salaryField.getText();
        String experience = experienceField.getText();
        String location = locationField.getText();
        String description = descriptionArea.getText();

        JobOffer jobOffer = new JobOffer();
        // Set job offer details
        jobOffer.setCompanyId(companyId);; // Assuming company ID 1 for now
        jobOffer.setTitle(jobTitle);
        jobOffer.setDescription(description);
        jobOffer.setSalary(Float.parseFloat(salary));
        jobOffer.setLocation(location);

        jobOfferDAO.addJobOffer(jobOffer);
    }
}
