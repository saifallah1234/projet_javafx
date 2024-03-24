import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SignUpUserController {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private ChoiceBox<String> imagePicker;

    @FXML
    private Button signupBtn;

    @FXML
    private ImageView Defaultview;

    @FXML
    private ImageView companyview;

    @FXML
    private ImageView userview;

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
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String phone = phoneTextField.getText();
        String imageType = imagePicker.getValue();

        if (validateFields(firstName, lastName, email, username, password, confirmPassword, phone)) {
            if (Util.signupUser(firstName, lastName, email, username, password, phone, imageType)) {
                Util.switchToDashboard();
            } else {
                errorLabel.setText("failed to sign up. Please try again.");
            }
        } else {
            errorLabel.setText("please fill in all fields and ensure passwords match.");
        }
    }

    private boolean validateFields(String firstName, String lastName, String email, String username, String password, String confirmPassword, String phone) {
        return !firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !username.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && !phone.isEmpty() && password.equals(confirmPassword);
    }
}
