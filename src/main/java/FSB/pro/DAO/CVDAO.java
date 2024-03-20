package FSB.pro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import FSB.pro.models.CV;
import FSB.pro.utils.ConxDB;

public class CVDAO {
    private Connection connection;

    public CVDAO() {
        // Initialize the connection
        connection = ConxDB.getInstance();
    }

    // Method to add a new CV entry to the database
    public void addCV(CV cv) {
        try {
            String query = "INSERT INTO cv (experience_id, skills_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, cv.getExperienceId());
            preparedStatement.setLong(2, cv.getSkillsId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a CV by ID from the database
    public CV getCVById(Long cvId) {
        CV cv = null;
        try {
            String query = "SELECT * FROM cv WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, cvId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cv = new CV();
                cv.setId(resultSet.getLong("id"));
                cv.setExperienceId(resultSet.getLong("experience_id"));
                cv.setSkillsId(resultSet.getLong("skills_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cv;
    }

    // Method to get all CV entries from the database
    public List<CV> getAllCVs() {
        List<CV> cvList = new ArrayList<>();
        try {
            String query = "SELECT * FROM cv";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CV cv = new CV();
                cv.setId(resultSet.getLong("id"));
                cv.setExperienceId(resultSet.getLong("experience_id"));
                cv.setSkillsId(resultSet.getLong("skills_id"));

                cvList.add(cv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cvList;
    }

    // Method to update an existing CV entry in the database
    public void updateCV(CV cv) {
        try {
            String query = "UPDATE cv SET experience_id=?, skills_id=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, cv.getExperienceId());
            preparedStatement.setLong(2, cv.getSkillsId());
            preparedStatement.setLong(3, cv.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a CV entry from the database
    public void deleteCV(Long cvId) {
        try {
            String query = "DELETE FROM cv WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, cvId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
