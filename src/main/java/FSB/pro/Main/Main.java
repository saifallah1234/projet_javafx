package FSB.pro.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    //test signup
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/FSB/pro/fxml/views/signup.fxml"));
        primaryStage.setTitle("User Signup");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    //test chat
    /*public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/FSB/pro/fxml/views/ChatView.fxml"));
        primaryStage.setTitle("User Signup");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    } */
    
    
}
