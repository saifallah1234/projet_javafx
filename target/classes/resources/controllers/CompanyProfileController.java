import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javax.swing.text.html.ListView;
import java.util.List;

public class CompanyProfileController {

    @FXML
    private ImageView companyLogo;

    @FXML
    private Label companyNameLabel;

    @FXML
    private Label companyBioLabel;

    @FXML
    private Button followButton;

    @FXML
    private ListView<String> servicesListView;

    @FXML
    private ListView<String> teamListView;

    @FXML
    private ListView<String> companyProjectsListView;

    @FXML
    private Button likeButton;

    @FXML
    private Label likeCountLabel;

    @FXML
    private Button viewJobOpeningsButton;

    @FXML
    private ListView<String> companyNewsListView;
    @FXML
    private ImageView userImageView;

    @FXML
    private Label usernameLabel;
    @FXML
    private ListView userOnlineList;
    @FXML
    private Label userOnlineCountLabel;


    public void initialize() {
        userImageView.setImage(Util.fetchProfileImage());
        usernameLabel.setText(Util.fetchUsername());
        userOnlineList.setItems(Util.fetchOnlineList());
        userOnlineCountLabel.setText("Online: " + Util.fetchOnlineCount())
        companyNameLabel.setText(companyProfile.getName());
        companyBioLabel.setText(companyProfile.getBio());
        servicesListView.getItems().addAll(companyProfile.getServices());
        teamListView.getItems().addAll(companyProfile.getTeamMembers());
        companyProjectsListView.getItems().addAll(companyProfile.getProjects());
        likeCountLabel.setText("Likes: " + Util.fetchCompanyLikeCount());
        companyLogo.setImage(Util.fetchCompanyLogo());
        List<String> companyNews = Util.fetchCompanyNews();
        companyNewsListView.getItems().addAll(companyNews);
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
    private void handleFollowButtonAction() {
        Util.followCompany();
    }

    @FXML
    private void handleLikeButtonAction() {
        Util.likeCompany();
        likeCountLabel.setText("Likes: " + Util.fetchCompanyLikeCount());
    }

    @FXML
    private void handleViewJobOpeningsBtn() {
        Util.viewJobOpenings();
    }

    @FXML
    private void handleLikeNewsButtonAction() {
        String selectedPost = companyNewsListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Util.likeNews(selectedPost);
        }
    }

    @FXML
    private void handleCommentNewsButtonAction() {
        String selectedPost = companyNewsListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Util.commentOnNews(selectedPost);
        }
    }

    @FXML
    private void handleShareNewsButtonAction() {
        String selectedPost = companyNewsListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Util.shareNews(selectedPost);
        }
    }
}
