import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SignUpCompanyController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField companyEmailTextField;

    @FXML
    private TextField websiteTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField companyPhoneTextField;

    @FXML
    private ImageView Defaultview;

    @FXML
    private ImageView companyview;

    @FXML
    private ImageView userview;

    @FXML
    private Button signupBtn;

    @FXML
    private Button MinimizedBtn;

    @FXML
    private Button CloseBtn;

    @FXML
    private Label errorLabel;

    @FXML
    private void initialize() {
        errorLabel.setText("");
    }

    @FXML
    private void closeSystem() {
        Util.closeApplicaton();
    }

    @FXML
    private void signupButtonAction() {
        String name = nameTextField.getText();
        String description = descriptionTextField.getText();
        String email = companyEmailTextField.getText();
        String website = websiteTextField.getText();
        String location = locationTextField.getText();
        String phone = companyPhoneTextField.getText();

        if (validateFields(name, description, email, website, location, phone)) {
            if (Util.signupCompany(name, description, email, website, location, phone)) {
                Util.switchToDashboard();
            } else {
                errorLabel.setText("Failed to sign up. Please try again.");
            }
        } else {
            errorLabel.setText("Please fill in all fields.");
        }
    }

    private boolean validateFields(String name, String description, String email, String website, String location, String phone) {
        return !name.isEmpty() && !description.isEmpty() && !email.isEmpty() && !website.isEmpty() && !location.isEmpty() && !phone.isEmpty();
    }
}
