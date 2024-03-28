package FSB.pro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import FSB.pro.models.Company;
import FSB.pro.utils.ConxDB;

public class CompanyDAO {
    private Connection connection;

    public CompanyDAO() {
        // Initialize the connection
        connection = ConxDB.getInstance();
    }
    public void addCompany(Company company) {
        try {
            String query = "INSERT INTO company (name, description, email, website, location, phone_number, logo, likes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getDescription());
            preparedStatement.setString(3, company.getEmail());
            preparedStatement.setString(4, company.getWebsite());
            preparedStatement.setString(5, company.getLocation());
            preparedStatement.setString(6, company.getPhoneNumber());
            preparedStatement.setString(7, company.getLogo());
            preparedStatement.setInt(8, company.getLikes());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a company by ID from the database
    public Company getCompanyById(Long companyId) {
        Company company = null;
        try {
            String query = "SELECT * FROM company WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, companyId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                company = new Company();
                company.setId(resultSet.getLong("id"));
                company.setName(resultSet.getString("name"));
                company.setDescription(resultSet.getString("description"));
                company.setEmail(resultSet.getString("email"));
                company.setWebsite(resultSet.getString("website"));
                company.setLocation(resultSet.getString("location"));
                company.setPhoneNumber(resultSet.getString("phone_number"));
                company.setLogo(resultSet.getString("logo"));
                company.setLikes(resultSet.getInt("likes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    // Method to update company description in the database
    public void updateCompanyDescription(Long companyId, String newDescription) {
        try {
            String query = "UPDATE company SET description=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newDescription);
            preparedStatement.setLong(2, companyId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new service for a company
    public void addService(Long companyId, String service) {
        try {
            String query = "INSERT INTO company_services (company_id, service) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, companyId);
            preparedStatement.setString(2, service);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new team member for a company
    public void addTeamMember(Long companyId, String teamMember) {
        try {
            String query = "INSERT INTO company_team (company_id, team_member) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, companyId);
            preparedStatement.setString(2, teamMember);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new project for a company
    public void addProject(Long companyId, String project) {
        try {
            String query = "INSERT INTO company_projects (company_id, project) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, companyId);
            preparedStatement.setString(2, project);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to increment likes for a company
    public void incrementLikes(Long companyId) {
        try {
            String query = "UPDATE company SET likes = likes + 1 WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, companyId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all news for a company
    public List<String> getCompanyNews(Long companyId) {
        List<String> companyNews = new ArrayList<>();
        try {
            String query = "SELECT news FROM company_news WHERE company_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, companyId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                companyNews.add(resultSet.getString("news"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyNews;
    }
    public List<String> getAllServices(Long companyId) {
        List<String> services = new ArrayList<>();
        try {
            String query = "SELECT service FROM company_services WHERE company_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, companyId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                services.add(resultSet.getString("service"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }
    public void updateCompany(Company company) {
        try {
            String query = "UPDATE company SET name=?, description=?, email=?, website=?, location=?, phone_number=?, logo=?, likes=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getDescription());
            preparedStatement.setString(3, company.getEmail());
            preparedStatement.setString(4, company.getWebsite());
            preparedStatement.setString(5, company.getLocation());
            preparedStatement.setString(6, company.getPhoneNumber());
            preparedStatement.setString(7, company.getLogo());
            preparedStatement.setInt(8, company.getLikes());
            preparedStatement.setLong(9, company.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Other methods for deleting company services, team members, projects, etc. as needed...

}
