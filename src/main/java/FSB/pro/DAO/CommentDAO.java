package FSB.pro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import FSB.pro.models.Comment;
import FSB.pro.utils.ConxDB;

public class CommentDAO {
    private Connection connection;

    public CommentDAO() {
        // Initialize the connection
        connection = ConxDB.getInstance();
    }

    // Method to add a new comment to the database
    public void addComment(Comment comment) {
        try {
            String query = "INSERT INTO comment (text, sendDate, send_time, sender_id, post_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, comment.getText());
            preparedStatement.setDate(2, java.sql.Date.valueOf(comment.getSendDate()));
            preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(comment.getSendTime()));
            preparedStatement.setLong(4, comment.getSenderId());
            preparedStatement.setLong(5, comment.getPostId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all comments for a specific post
    public List<Comment> getCommentsByPostId(Long postId) {
        List<Comment> commentList = new ArrayList<>();
        try {
            String query = "SELECT * FROM comment WHERE post_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, postId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setText(resultSet.getString("text"));
                comment.setSendDate(resultSet.getDate("sendDate").toLocalDate());
                comment.setSendTime(resultSet.getTimestamp("send_time").toLocalDateTime());
                comment.setSenderId(resultSet.getLong("sender_id"));
                comment.setPostId(resultSet.getLong("post_id"));

                commentList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }

    // Method to get all comments sent by a specific user
    public List<Comment> getCommentsBySenderId(Long senderId) {
        List<Comment> commentList = new ArrayList<>();
        try {
            String query = "SELECT * FROM comment WHERE sender_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, senderId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setText(resultSet.getString("text"));
                comment.setSendDate(resultSet.getDate("sendDate").toLocalDate());
                comment.setSendTime(resultSet.getTimestamp("send_time").toLocalDateTime());
                comment.setSenderId(resultSet.getLong("sender_id"));
                comment.setPostId(resultSet.getLong("post_id"));

                commentList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }
}
