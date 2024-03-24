import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PostAJobController {
    @FXML
    private TextField jobTitleField;

    @FXML
    private TextField salaryField;


    @FXML
    private TextArea requiredExperienceArea;

    @FXML
    private TextArea jobDescriptionArea;

    @FXML
    private TextField locationField;

    @FXML
    private void postOffer() {
        String jobTitle = jobTitleField.getText();
        String salary = salaryField.getText();
        String educationLevel = educationComboBox.getValue();
        String requiredExperience = requiredExperienceArea.getText();
        String jobDescription = jobDescriptionArea.getText();
        String location = locationField.getText();
        util.postJobOffer(jobTitle, salary, educationLevel, requiredExperience, jobDescription, location);
}
@FXML
    private void cancel() {
    util.returnToMainProfile();
    }
}
