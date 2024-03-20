package FSB.pro.DAO;
import FSB.pro.models.Skill;
import FSB.pro.utils.ConxDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SkillDAO {
    private Connection connection;

    public SkillDAO() {
        // Initialize the connection
        connection = ConxDB.getInstance();
    }

    // Method to add a new skill to the database
    public void addSkill(Skill skill) {
        try {
            String query = "INSERT INTO skills (name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, skill.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a skill by ID from the database
    public Skill getSkillById(Long skillId) {
        Skill skill = null;
        try {
            String query = "SELECT * FROM skills WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, skillId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                skill = new Skill();
                skill.setId(resultSet.getLong("id"));
                skill.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    // Method to get all skills from the database
    public List<Skill> getAllSkills() {
        List<Skill> skillList = new ArrayList<>();
        try {
            String query = "SELECT * FROM skills";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Skill skill = new Skill();
                skill.setId(resultSet.getLong("id"));
                skill.setName(resultSet.getString("name"));

                skillList.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skillList;
    }

    // Method to update an existing skill in the database
    public void updateSkill(Skill skill) {
        try {
            String query = "UPDATE skills SET name=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setLong(2, skill.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a skill from the database
    public void deleteSkill(Long skillId) {
        try {
            String query = "DELETE FROM skills WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, skillId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
