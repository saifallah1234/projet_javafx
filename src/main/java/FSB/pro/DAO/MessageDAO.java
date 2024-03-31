package FSB.pro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import FSB.pro.models.Messages;
import FSB.pro.models.User;
import FSB.pro.utils.ConxDB;

public class MessageDAO {
    private Connection connection;

    public MessageDAO() {
        // Initialize the connection
        connection = ConxDB.getInstance();
    }

    // Method to add a new message to the database
    public void addMessage(Messages message) {
        try {
            String query = "INSERT INTO message (text, sendDate, sender_id, addressee_id) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, message.getText());
            preparedStatement.setObject(2, message.getSendDate());
            preparedStatement.setLong(3, message.getSender().getId());
            preparedStatement.setLong(4, message.getAddressee().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get messages sent by a user
    public List<Messages> getMessagesSentByUser(long user) {
        List<Messages> messages = new ArrayList<>();
        try {
            String query = "SELECT * FROM message WHERE sender_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, user);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Messages message = new Messages();
                message.setId(resultSet.getLong("id"));
                message.setText(resultSet.getString("text"));
                LocalDateTime dateTime = resultSet.getTimestamp("sendDate").toLocalDateTime();
                message.setSendDate(dateTime);
                // You might need to fetch sender and addressee separately
                // This assumes you have a method to get user by ID
                User sender = getUserById(resultSet.getLong("sender_id"));
                User addressee = getUserById(resultSet.getLong("addressee_id"));
                message.setSender(sender);
                message.setAddressee(addressee);

                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    // Method to get messages received by a user
    public List<Messages> getMessagesReceivedByUser(long user) {
        List<Messages> messages = new ArrayList<>();
        try {
            String query = "SELECT * FROM message WHERE addressee_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,user);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Messages message = new Messages();
                message.setId(resultSet.getLong("id"));
                message.setText(resultSet.getString("text"));
                LocalDateTime dateTime = resultSet.getTimestamp("sendDate").toLocalDateTime();
                message.setSendDate(dateTime);
                // You might need to fetch sender and addressee separately
                // This assumes you have a method to get user by ID
                User sender = getUserById(resultSet.getLong("sender_id"));
                User addressee = getUserById(resultSet.getLong("addressee_id"));
                message.setSender(sender);
                message.setAddressee(addressee);

                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    // Helper method to get a user by ID
    private User getUserById(Long userId) {
        UserDAO userDAO = new UserDAO();
        return userDAO.getUserById(userId);
    }
}
