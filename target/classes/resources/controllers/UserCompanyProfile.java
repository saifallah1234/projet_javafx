import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import java.util.List;

public class CompanyProfileController {

    @FXML
    private ImageView companyLogo;

    @FXML
    private Label companyNameLabel;

    @FXML
    private Label companyBioLabel;

    @FXML
    private ListView<String> servicesListView;

    @FXML
    private ListView<String> teamListView;

    @FXML
    private ListView<String> companyProjectsListView;

    @FXML
    private Label likeCountLabel;

    @FXML
    private ListView<String> companyNewsListView;

    @FXML
    private Button viewJobOpeningsButton;

    @FXML
    private Button postNewJobButton;

    @FXML
    private Button editProfileButton;

    public void initialize() {
        CompanyProfile companyProfile = Util.fetchCompanyProfile();
        companyNameLabel.setText(companyProfile.getName());
        companyBioLabel.setText(companyProfile.getBio());
        List<String> servicesList = Util.fetchServices();
        servicesListView.getItems().addAll(servicesList);
        List<String> teamList = Util.fetchTeamMembers();
        teamListView.getItems().addAll(teamList);
        List<String> projectList = Util.fetchCompanyProjects();
        companyProjectsListView.getItems().addAll(projectList);
        likeCountLabel.setText("Likes: " + Util.fetchCompanyLikes());
        List<String> companyNews = Util.fetchCompanyNews();
        companyNewsListView.getItems().addAll(companyNews);
        companyLogo.setImage(Util.fetchCompanyLogo());
    }

    @FXML
    private void handleViewJobOpeningsBtn() {
        Util.viewJobOpenings();
    }

    @FXML
    private void postNewJobButtonAction() {
        Util.postNewJob();
    }

    @FXML
    private void editProfileButtonAction() {
        Util.SwitchToEditPage();
    }

    @FXML
    private void handleLikeNewsButtonAction() {
        String selectedPost = companyNewsListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Util.likeCompanyNews(selectedPost);
        }
    }

    @FXML
    private void handleCommentNewsButtonAction() {
        String selectedPost = companyNewsListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Util.commentOnCompanyNews(selectedPost);
        }
    }

    @FXML
    private void handleShareNewsButtonAction() {
        String selectedPost = companyNewsListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            Util.shareCompanyNews(selectedPost);
        }
    }
}
