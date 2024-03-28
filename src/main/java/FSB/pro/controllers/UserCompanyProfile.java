package FSB.pro.controllers;

import java.io.File;
import java.util.List;

import FSB.pro.DAO.CompanyDAO;
import FSB.pro.DAO.PostDAO;
import FSB.pro.models.Company;
import FSB.pro.models.Post;
import FSB.pro.services.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class UserCompanyProfile {
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
    private ListView<Post> companyNewsListView;

    @FXML
    private Button viewJobOpeningsButton;

    @FXML
    private Button comment;

    @FXML
    private Button share;

    @FXML
    private Button postNewJobButton;

    @FXML
    private Button editProfileButton;

    public Company companyProfile;
    public CompanyDAO companyDAO;
    public PostDAO postDAO;
    public Post post;
    private long userId;
    private long companyId;

    public void companyId(long id){
        this.companyId=id;
    }
    public void userId(long id){
        this.userId=id;
    }

    public void initialize() {
        // Simulating fetching data from database
        companyProfile = companyDAO.getCompanyById(companyId);
        companyNameLabel.setText(companyProfile.getName());
        companyBioLabel.setText(companyProfile.getDescription());
        List<String> servicesList = fetchServices();
        servicesListView.getItems().addAll(servicesList);
        List<String> teamList = fetchTeamMembers();
        teamListView.getItems().addAll(teamList);
        List<String> projectList = fetchCompanyProjects();
        companyProjectsListView.getItems().addAll(projectList);
        likeCountLabel.setText("Likes: " + fetchCompanyLikes());
        companyNewsListView.getItems().addAll(fetchCompanyNews());
        companyLogo.setImage(new Image(companyProfile.getLogo()));
       
    }

    private List<String> fetchServices() {
        
        return companyProfile.getServices();
    }

    private List<String> fetchTeamMembers() {
        return companyProfile.getTeamMembers();
    }

    private List<String> fetchCompanyProjects() {
        return companyProfile.getProjects();
    }

    private int fetchCompanyLikes() {
        // Fetch company likes data, replace with actual implementation
        return companyProfile.getLikes();
    }

    private List<Post> fetchCompanyNews() {
        return postDAO.getPostBySenderId(companyId);
        
    }

    private void setCompanyLogoImage() {
        if (companyProfile != null) {
            String imagePath = companyProfile.getLogo(); // Assuming this is the file path stored in the database
            File imageFile = new File(imagePath);
    
            if (imageFile.exists()) {
                Image image = new Image(imageFile.toURI().toString());
                companyLogo.setImage(image);
            } else {
                // Default image if the file doesn't exist
                Image defaultImage = new Image("/path/to/default/image.png");
                companyLogo.setImage(defaultImage);
            }
        } else {
            // Set a default image if companyProfile is null
            Image defaultImage = new Image("/path/to/default/image.png");
            companyLogo.setImage(defaultImage);
        }
    }
    
    

    @FXML
    private void handleViewJobOpeningsBtn() {
        viewJobOpenings();
    }

    @FXML
    private void postNewJobButtonAction() {
        postNewJob();
    }

    @FXML
    private void editProfileButtonAction() {
        switchToEditPage();
    }

    @FXML
    private void handleLikeNewsButtonAction() {
        Post selectedPost = companyNewsListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            likeCompanyNews(selectedPost);
        }
    }

    @FXML
    private void handleCommentNewsButtonAction() {
        Post selectedPost = companyNewsListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            commentOnCompanyNews(selectedPost);
        }
    }

    @FXML
    private void handleShareNewsButtonAction() {
        Post selectedPost = companyNewsListView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            shareCompanyNews(selectedPost);
        }
    }

    // Placeholder methods, replace with actual implementations
    private void viewJobOpenings() {
        System.out.println("View Job Openings");
        // Implement functionality to view job openings
    }

    private void postNewJob() {
        System.out.println("Post New Job");
        // Implement functionality to post a new job
    }

    private void switchToEditPage() {
        System.out.println("Switch to Edit Page");
        // Implement functionality to switch to edit profile page
    }

    private void likeCompanyNews(Post news) {
        System.out.println("Like News: " + news);
        postDAO.incrementReaction(news.getId());
    }

    private void commentOnCompanyNews(Post news) {
        Stage stage = (Stage) comment.getScene().getWindow();
        CommentController commentController = SceneSwitcher.switchScene("Comment.fxml", stage);
        commentController.postId(news.getId());
    }

    private void shareCompanyNews(Post news) {
        Stage stage = (Stage) share.getScene().getWindow(); // change comment to share
        PostController postController =  SceneSwitcher.switchScene("AddPost.fxml", stage);
        postController.userId(userId);
        postController.postId(news.getId());
        
        
    }
}
