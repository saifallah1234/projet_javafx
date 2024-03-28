public class LoginController {
    @FXML
    private TextField usernameTextfield;
    @FXML
    private PasswordField password;
    @FXML
    private ChoiceBox<String> imagePicker;
    @FXML
    private Label errorLabel;

    @FXML
    private void initialize() {
        errorLabel.setText("");
    }

    private void loginButtonAction() {
        String selectedOption = imagePicker.getValue(); // Assuming imagePicker is the id for your ChoiceBox
        String username = usernameTextfield.getText();
        String enteredPassword = password.getText();

        if (Util.login(selectedOption, username, enteredPassword)) {
            Util.switchtodashboard();
        } else {
            errorLabel.setText("Invalid username or password");
        }
    }

}
