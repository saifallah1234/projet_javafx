package FSB.pro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import FSB.pro.models.User;
import FSB.pro.utils.ConxDB;

public class FriendshipDAO {
    private Connection connection;

    public FriendshipDAO() {
        // Initialize the connection
        connection = ConxDB.getInstance();
    }

    public void addFriendship(int userId1, int userId2) {
        try {
            String query = "INSERT INTO friendship (user_id1, user_id2) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId1);
            preparedStatement.setInt(2, userId2);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<User> getFriendsByUserId(Long userId) {
        List<User> friends = new ArrayList<>();
        try {
            String query = "SELECT user.* FROM user JOIN friendship ON user.user_id = friendship.user_id1 WHERE friendship.user_id2 = ? UNION SELECT user.* FROM user JOIN friendship ON user.user_id = friendship.user_id2 WHERE friendship.user_id1 = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                // Assuming User class has setters for all fields
                user.setId(resultSet.getLong("user_id"));
                // Set other fields...

                friends.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }
}
