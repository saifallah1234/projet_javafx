package FSB.pro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import FSB.pro.models.Experience;
import FSB.pro.utils.ConxDB;

public class ExperienceDAO {
    private Connection connection;

    public ExperienceDAO() {
        // Initialize the connection
        connection = ConxDB.getInstance();
    }

    // Method to add a new experience to the database
    public void addExperience(Experience experience) {
        try {
            String query = "INSERT INTO experience (description) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, experience.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get an experience by ID from the database
    public Experience getExperienceById(Long experienceId) {
        Experience experience = null;
        try {
            String query = "SELECT * FROM experience WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, experienceId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                experience = new Experience();
                experience.setId(resultSet.getLong("id"));
                experience.setDescription(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experience;
    }

    // Method to get all experiences from the database
    public List<Experience> getAllExperiences() {
        List<Experience> experienceList = new ArrayList<>();
        try {
            String query = "SELECT * FROM experience";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Experience experience = new Experience();
                experience.setId(resultSet.getLong("id"));
                experience.setDescription(resultSet.getString("description"));

                experienceList.add(experience);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experienceList;
    }

    // Method to update an existing experience in the database
    public void updateExperience(Experience experience) {
        try {
            String query = "UPDATE experience SET description=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, experience.getDescription());
            preparedStatement.setLong(2, experience.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete an experience from the database
    public void deleteExperience(Long experienceId) {
        try {
            String query = "DELETE FROM experience WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, experienceId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
