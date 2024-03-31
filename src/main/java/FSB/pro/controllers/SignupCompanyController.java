package FSB.pro.controllers;

import java.io.IOException;
import java.util.Random;

import FSB.pro.services.CompanyService;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignupCompanyController {

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
    private PasswordField passwordField; // Added password field

    @FXML
    private Button signupBtn;

    @FXML
    private Button CloseBtn;

    @FXML
    private Label errorLabel;
    @FXML
    private BorderPane borderPane;

    private CompanyService companyService = new CompanyService();

    @FXML
    private void initialize() {
        errorLabel.setText("");
        int numberOfSquares = 30;
        while (numberOfSquares > 0){
            generateAnimation();
            numberOfSquares--;
        }
    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) CloseBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void signupButtonAction() {
        String name = nameTextField.getText();
        String description = descriptionTextField.getText();
        String email = companyEmailTextField.getText();
        String website = websiteTextField.getText();
        String location = locationTextField.getText();
        String phone = companyPhoneTextField.getText();
        String password = passwordField.getText(); // Retrieve password
    
        if (validateFields(name, description, email, website, location, phone, password)) {
            if (!companyService.companyTest(name, description, email, website, location, phone, password)) {
                switchToLogin();
            } else {
                // Company already exists
            }
        }
    }
    
    private boolean validateFields(String name, String description, String email, String website, String location, String phone, String password) {
        boolean allFieldsFilled = !name.isEmpty() && !description.isEmpty() && !email.isEmpty() && !website.isEmpty() && !location.isEmpty() && !phone.isEmpty() && !password.isEmpty();
        if (!allFieldsFilled) {
            showAlert(AlertType.ERROR, "Empty Fields", "Please fill in all fields.");
        }
        return allFieldsFilled;
    }
    
    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    


    @FXML
    private void loginInsteadButtonAction() {
        switchToLogin();
    }

    private void switchToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/LoginView.fxml"));
            Parent loginInterface = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(loginInterface));
            stage.show();

            // Close the current signup window
            Stage currentStage = (Stage) signupBtn.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void signUpAsUserButtonAction() {
        switchToSignUpUser();
    }

    private void switchToSignUpUser() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/signupuser.fxml"));
            Parent loginInterface = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(loginInterface));
            stage.show();

            // Close the current signup window
            Stage currentStage = (Stage) signupBtn.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generateAnimation() {
        Random rand = new Random();
        int sizeOfSquare = rand.nextInt(50) + 1;
        int speedOfSquare = rand.nextInt(10) + 5;
        int startXPoint = rand.nextInt(420);
        int startYPoint = rand.nextInt(350);
        int direction = rand.nextInt(5) + 1;

        KeyValue moveXAxis = null;
        KeyValue moveYAxis = null;
        Rectangle r1 = null;

        switch (direction) {
            case 1:
                // MOVE LEFT TO RIGHT
                r1 = new Rectangle(0, startYPoint, sizeOfSquare, sizeOfSquare);
                moveXAxis = new KeyValue(r1.xProperty(), 350 - sizeOfSquare);
                break;
            case 2:
                // MOVE TOP TO BOTTOM
                r1 = new Rectangle(startXPoint, 0, sizeOfSquare, sizeOfSquare);
                moveYAxis = new KeyValue(r1.yProperty(), 420 - sizeOfSquare);
                break;
            case 3:
                // MOVE LEFT TO RIGHT, TOP TO BOTTOM
                r1 = new Rectangle(startXPoint, 0, sizeOfSquare, sizeOfSquare);
                moveXAxis = new KeyValue(r1.xProperty(), 350 - sizeOfSquare);
                moveYAxis = new KeyValue(r1.yProperty(), 420 - sizeOfSquare);
                break;
            case 4:
                // MOVE BOTTOM TO TOP
                r1 = new Rectangle(startXPoint, 420 - sizeOfSquare, sizeOfSquare, sizeOfSquare);
                moveYAxis = new KeyValue(r1.yProperty(), 0);
                break;
            case 5:
                // MOVE RIGHT TO LEFT
                r1 = new Rectangle(420 - sizeOfSquare, startYPoint, sizeOfSquare, sizeOfSquare);
                moveXAxis = new KeyValue(r1.xProperty(), 0);
                break;
            case 6:
                // MOVE RIGHT TO LEFT, BOTTOM TO TOP
                r1 = new Rectangle(startXPoint, 0, sizeOfSquare, sizeOfSquare);
                moveXAxis = new KeyValue(r1.xProperty(), 350 - sizeOfSquare);
                moveYAxis = new KeyValue(r1.yProperty(), 420 - sizeOfSquare);
                break;
            default:
                System.out.println("default");
        }

        r1.setFill(Color.web("#F89406"));
        r1.setOpacity(0.1);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(speedOfSquare * 1000), moveXAxis, moveYAxis);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        borderPane.getChildren().add(borderPane.getChildren().size() - 1, r1);
    }
}

