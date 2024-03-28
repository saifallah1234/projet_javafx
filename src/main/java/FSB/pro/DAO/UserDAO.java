package FSB.pro.DAO;
import FSB.pro.models.User;
import FSB.pro.utils.ConxDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        // Initialize the connection
        connection = ConxDB.getInstance();
    }

    // Method to add a new user to the database
    public void addUser(User user) {
        try {
            String query = "INSERT INTO user (username, email, firstName, lastName, password, number,bio, matchingPassword, role, photo) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setString(7, user.getBio());
            preparedStatement.setString(8, user.getMatchingPassword());
            preparedStatement.setString(9, user.getRole());
            preparedStatement.setString(10, user.getPhoto());
    
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    // Method to update an existing user in the database
    public void updateUser(User user) {
        try {
            String query = "UPDATE user SET username=?, email=?, firstName=?, lastName=?, password=?, number=?,bio=?, msgCount=?, notCount=?, matchingPassword=?, role=?, logged=?, friendsCount=?, photo=? " +
                    "WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setString(7, user.getBio());
            preparedStatement.setInt(8, user.getMsgCount());
            preparedStatement.setInt(9, user.getNotCount());
            preparedStatement.setString(10, user.getMatchingPassword());
            preparedStatement.setString(11, user.getRole());
            preparedStatement.setBoolean(12, user.isLogged());
            preparedStatement.setInt(13, user.getFriendsCount());
            preparedStatement.setString(14, user.getPhoto());
            preparedStatement.setLong(15, user.getId());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated successfully.");
            }
            // Commit the changes
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            // Rollback changes if an exception occurs
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        }
    }

    // Method to delete a user from the database
    public void deleteUser(long id) {
        try {
            String query = "DELETE FROM user WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a user by ID from the database
    public User getUserById(Long userId) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setBio(resultSet.getString("bio"));
                user.setFriendsCount(resultSet.getInt("friendsCount"));
                user.setMsgCount(resultSet.getInt("msgCount"));
                user.setNotCount(resultSet.getInt("notCount"));
                user.setMatchingPassword(resultSet.getString("matchingPassword"));
                user.setRole(resultSet.getString("role"));
                user.setLogged(resultSet.getBoolean("logged"));
                user.setPhoneNumber(resultSet.getString("number"));
                user.setPhoto(resultSet.getString("photo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Method to get all users from the database
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM user";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setBio(resultSet.getString("bio"));
                user.setFriendsCount(resultSet.getInt("friendsCount"));
                user.setMsgCount(resultSet.getInt("msgCount"));
                user.setNotCount(resultSet.getInt("notCount"));
                user.setMatchingPassword(resultSet.getString("matchingPassword"));
                user.setRole(resultSet.getString("role"));
                user.setLogged(resultSet.getBoolean("logged"));
                user.setPhoneNumber(resultSet.getString("number"));
                user.setPhoto(resultSet.getString("photo"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public User findByEmail(String email) {
        User user = new User() ;
        try {
            String query = "SELECT * FROM user WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setBio(resultSet.getString("bio"));
                user.setFriendsCount(resultSet.getInt("friendsCount"));
                user.setMsgCount(resultSet.getInt("msgCount"));
                user.setNotCount(resultSet.getInt("notCount"));
                user.setMatchingPassword(resultSet.getString("matchingPassword"));
                user.setRole(resultSet.getString("role"));
                user.setLogged(resultSet.getBoolean("logged"));
                user.setPhoneNumber(resultSet.getString("number"));
                user.setPhoto(resultSet.getString("photo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Method to find users who are logged in
    public List<User> findByLogged() {
        List<User> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM user WHERE logged = true";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setBio(resultSet.getString("bio"));
                user.setFriendsCount(resultSet.getInt("friendsCount"));
                user.setMsgCount(resultSet.getInt("msgCount"));
                user.setNotCount(resultSet.getInt("notCount"));
                user.setMatchingPassword(resultSet.getString("matchingPassword"));
                user.setRole(resultSet.getString("role"));
                user.setLogged(resultSet.getBoolean("logged"));
                user.setPhoneNumber(resultSet.getString("number"));
                user.setPhoto(resultSet.getString("photo"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Method to find users by first name
    public List<User> findByFirstName(String firstName) {
        List<User> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM user WHERE first_name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setBio(resultSet.getString("bio"));
                user.setFriendsCount(resultSet.getInt("friendsCount"));
                user.setMsgCount(resultSet.getInt("msgCount"));
                user.setNotCount(resultSet.getInt("notCount"));
                user.setMatchingPassword(resultSet.getString("matchingPassword"));
                user.setRole(resultSet.getString("role"));
                user.setLogged(resultSet.getBoolean("logged"));
                user.setPhoneNumber(resultSet.getString("number"));
                user.setPhoto(resultSet.getString("photo"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Method to find user by ID
    public User findById(Long id) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setBio(resultSet.getString("bio"));
                user.setFriendsCount(resultSet.getInt("friendsCount"));
                user.setMsgCount(resultSet.getInt("msgCount"));
                user.setNotCount(resultSet.getInt("notCount"));
                user.setMatchingPassword(resultSet.getString("matchingPassword"));
                user.setRole(resultSet.getString("role"));
                user.setLogged(resultSet.getBoolean("logged"));
                user.setPhoneNumber(resultSet.getString("number"));
                user.setPhoto(resultSet.getString("photo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    // Method to get all skills of a user
public List<String> getAllSkills(Long userId) {
    List<String> skills = new ArrayList<>();
    try {
        String query = "SELECT skill_name FROM user_skills WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            skills.add(resultSet.getString("skill_name"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return skills;
}
// Method to get all experiences of a user
public List<String> getAllExperiences(Long userId) {
    List<String> experiences = new ArrayList<>();
    try {
        String query = "SELECT experience_name FROM user_experiences WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            experiences.add(resultSet.getString("experience_name"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return experiences;
}

    
}
