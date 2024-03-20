package FSB.pro.DAO;
import FSB.pro.models.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import FSB.pro.utils.ConxDB;


public class NotificationDAO {
    private Connection connection;

    public NotificationDAO() {
        // Initialize the connection
        connection = ConxDB.getInstance();
    }

    // Method to add a new notification to the database
    public void addNotification(Notification notification) {
        try {
            String query = "INSERT INTO notification (description, fromUser_id, toUser_id, date, time) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, notification.getDescription());
            preparedStatement.setLong(2, notification.getFromUserId());
            preparedStatement.setLong(3, notification.getToUserId());
            preparedStatement.setDate(4, java.sql.Date.valueOf(notification.getDate()));
            preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(notification.getTime()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all notifications for a user
    public List<Notification> getNotificationsForUser(Long userId) {
        List<Notification> notifications = new ArrayList<>();
        try {
            String query = "SELECT * FROM notification WHERE toUser_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Notification notification = new Notification();
                notification.setId(resultSet.getLong("id"));
                notification.setDescription(resultSet.getString("description"));
                notification.setFromUserId(resultSet.getLong("fromUser_id"));
                notification.setToUserId(resultSet.getLong("toUser_id"));
                notification.setDate(resultSet.getDate("date").toLocalDate());
                notification.setTime(resultSet.getTimestamp("time").toLocalDateTime());

                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    // Other methods for updating and deleting notifications as needed...

}
