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

import FSB.pro.DAO.JobOfferDAO;
import FSB.pro.DAO.UserDAO;
import FSB.pro.models.JobOffer;
import FSB.pro.models.User;

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
    UserDAO userDAO = new UserDAO();
    public long companyId;
    public void companyId(long id){
        this.companyId=id;
    }


    @FXML
    private void initialize() {
        userImageView.setImage(fetchProfileImage());
        usernameLabel.setText(fetchUsername());
        userOnlineList.setItems(fetchFriendList());
        userOnlineCountLabel.setText("Online: " + fetchOnlineCount());

        jobOfferDAO = new JobOfferDAO();
        JobOffer jobOffer = new JobOffer();
    }

    private Image fetchProfileImage() {

        User user = userDAO.getUserById(companyId);
    
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
        // Implement logic to show user profile
    }

    @FXML
    private void openChat() {
        // Implement logic to open chat
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
        // Implement logic to handle logout
    }

    @FXML
    private void closeApplication(MouseEvent event) {
        // Implement logic to close the application
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
