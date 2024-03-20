package FSB.pro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import FSB.pro.models.JobOffer;
import FSB.pro.utils.ConxDB;

public class JobOfferDAO {
    private Connection connection;

    public JobOfferDAO() {
        // Initialize the connection
        connection = ConxDB.getInstance();
    }

    // Method to add a new job offer to the database
    public void addJobOffer(JobOffer jobOffer) {
        try {
            String query = "INSERT INTO job_offer (company_id, title, description, salary, location, posted_date, deadline) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, jobOffer.getCompanyId());
            preparedStatement.setString(2, jobOffer.getTitle());
            preparedStatement.setString(3, jobOffer.getDescription());
            preparedStatement.setFloat(4, jobOffer.getSalary());
            preparedStatement.setString(5, jobOffer.getLocation());
            preparedStatement.setDate(6, jobOffer.getPostedDate());
            preparedStatement.setDate(7, jobOffer.getDeadline());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a job offer by ID from the database
    public JobOffer getJobOfferById(Long jobOfferId) {
        JobOffer jobOffer = null;
        try {
            String query = "SELECT * FROM job_offer WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, jobOfferId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                jobOffer = new JobOffer();
                jobOffer.setId(resultSet.getLong("id"));
                jobOffer.setCompanyId(resultSet.getLong("company_id"));
                jobOffer.setTitle(resultSet.getString("title"));
                jobOffer.setDescription(resultSet.getString("description"));
                jobOffer.setSalary(resultSet.getFloat("salary"));
                jobOffer.setLocation(resultSet.getString("location"));
                jobOffer.setPostedDate(resultSet.getDate("posted_date"));
                jobOffer.setDeadline(resultSet.getDate("deadline"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobOffer;
    }

    // Method to get all job offers from the database
    public List<JobOffer> getAllJobOffers() {
        List<JobOffer> jobOfferList = new ArrayList<>();
        try {
            String query = "SELECT * FROM job_offer";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JobOffer jobOffer = new JobOffer();
                jobOffer.setId(resultSet.getLong("id"));
                jobOffer.setCompanyId(resultSet.getLong("company_id"));
                jobOffer.setTitle(resultSet.getString("title"));
                jobOffer.setDescription(resultSet.getString("description"));
                jobOffer.setSalary(resultSet.getFloat("salary"));
                jobOffer.setLocation(resultSet.getString("location"));
                jobOffer.setPostedDate(resultSet.getDate("posted_date"));
                jobOffer.setDeadline(resultSet.getDate("deadline"));

                jobOfferList.add(jobOffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobOfferList;
    }

    // Method to update an existing job offer in the database
    public void updateJobOffer(JobOffer jobOffer) {
        try {
            String query = "UPDATE job_offer SET company_id=?, title=?, description=?, salary=?, location=?, posted_date=?, deadline=? " +
                    "WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, jobOffer.getCompanyId());
            preparedStatement.setString(2, jobOffer.getTitle());
            preparedStatement.setString(3, jobOffer.getDescription());
            preparedStatement.setFloat(4, jobOffer.getSalary());
            preparedStatement.setString(5, jobOffer.getLocation());
            preparedStatement.setDate(6, jobOffer.getPostedDate());
            preparedStatement.setDate(7, jobOffer.getDeadline());
            preparedStatement.setLong(8, jobOffer.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a job offer from the database
    public void deleteJobOffer(Long jobOfferId) {
        try {
            String query = "DELETE FROM job_offer WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, jobOfferId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
