package FSB.pro.DAO;
import FSB.pro.models.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import FSB.pro.utils.ConxDB;


public class PostDAO {
    private Connection connection;

    public PostDAO() {
        // Initialize the connection
        connection = ConxDB.getInstance();
    }

    // Method to add a new post to the database
    public void addPost(Post post) {
        try {
            String query = "INSERT INTO post (title, text, sendDate, sendTime, sender_id, reactions, comCount) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setDate(3, java.sql.Date.valueOf(post.getSendDate()));
            preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(post.getSendTime()));
            preparedStatement.setLong(5, post.getSenderId());
            preparedStatement.setInt(6, post.getReactions());
            preparedStatement.setInt(7, post.getComCount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void incrementReaction(long postId) {
        try {
            String query = "UPDATE post SET reactions = reactions + 1 WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, postId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Reaction incremented successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update an existing post in the database
    public void updatePost(Post post) {
        try {
            String query = "UPDATE post SET title=?, text=?, sendDate=?, sendTime=?, sender_id=?, reactions=?, comCount=? " +
                    "WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setDate(3, java.sql.Date.valueOf(post.getSendDate()));
            preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(post.getSendTime()));
            preparedStatement.setLong(5, post.getSenderId());
            preparedStatement.setInt(6, post.getReactions());
            preparedStatement.setInt(7, post.getComCount());
            preparedStatement.setLong(8, post.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Post updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a post from the database
    public void deletePost(long id) {
        try {
            String query = "DELETE FROM post WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all posts from the database
    public List<Post> getAllPosts() {
        List<Post> postList = new ArrayList<>();
        try {
            String query = "SELECT * FROM post";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setTitle(resultSet.getString("title"));
                post.setText(resultSet.getString("text"));
                post.setSendDate(resultSet.getDate("sendDate").toLocalDate());
                post.setSendTime(resultSet.getTimestamp("sendTime").toLocalDateTime());
                post.setSenderId(resultSet.getLong("sender_id"));
                post.setReactions(resultSet.getInt("reactions"));
                post.setComCount(resultSet.getInt("comCount"));

                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

    // Method to get a post by ID from the database
    public Post getPostById(Long postId) {
        Post post = null;
        try {
            String query = "SELECT * FROM post WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, postId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setTitle(resultSet.getString("title"));
                post.setText(resultSet.getString("text"));
                post.setSendDate(resultSet.getDate("sendDate").toLocalDate());
                post.setSendTime(resultSet.getTimestamp("sendTime").toLocalDateTime());
                post.setSenderId(resultSet.getLong("sender_id"));
                post.setReactions(resultSet.getInt("reactions"));
                post.setComCount(resultSet.getInt("comCount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }
    public List<Post> getPostBySenderId(Long Sender_id){
        List<Post> postList = new ArrayList<>();
        try {
            String query = "SELECT * FROM post WHERE sender_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, Sender_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setTitle(resultSet.getString("title"));
                post.setText(resultSet.getString("text"));
                post.setSendDate(resultSet.getDate("sendDate").toLocalDate());
                post.setSendTime(resultSet.getTimestamp("sendTime").toLocalDateTime());
                post.setSenderId(resultSet.getLong("sender_id"));
                post.setReactions(resultSet.getInt("reactions"));
                post.setComCount(resultSet.getInt("comCount"));

                postList.add(post);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
    }
    return postList;
    }
}

