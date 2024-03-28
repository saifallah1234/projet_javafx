package FSB.pro.controllers;

import FSB.pro.DAO.NotificationDAO;
import FSB.pro.models.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class NotificationController {
    @FXML
    private ListView<String> notificationListView;
    private ObservableList<String> notifications;
    private NotificationDAO notificationDAO;

    public NotificationController() {
        notifications = FXCollections.observableArrayList();
        notificationDAO = new NotificationDAO();
    }

    @FXML
    public void initialize() {
        notificationListView.setItems(notifications);
        fetchNotifications();
    }

    private void fetchNotifications() {
        // Get notifications from the database using the DAO
        List<Notification> fetchedNotifications = notificationDAO.getNotificationsForUser(/* Provide userId here */);

        // Clear current list and add fetched notifications to the observable list
        notifications.clear();
        for (Notification notification : fetchedNotifications) {
            notifications.add(notification.getDescription());
        }
    }
}
