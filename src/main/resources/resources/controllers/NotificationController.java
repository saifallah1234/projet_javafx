import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainController {
    @FXML
    private ListView<String> notificationListView;
    private ObservableList<String> notifications;
    public MainController() {
        notifications = FXCollections.observableArrayList();
    }
    @FXML
    public void initialize() {
        notificationListView.setItems(notifications);
        fetchNotifications();
    }

    private void fetchNotifications() {
        ObservableList<String> fetchedNotifications = Util.fetchNotification();
        notifications.clear();
        notifications.addAll(fetchedNotifications);
    }
}
