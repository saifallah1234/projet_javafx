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

    // Method to add a new company to the database
    public void addCompany(Company company) {
        try {
            String query = "INSERT INTO company (name, description, email, website, location, phone_number) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getDescription());
            preparedStatement.setString(3, company.getEmail());
            preparedStatement.setString(4, company.getWebsite());
            preparedStatement.setString(5, company.getLocation());
            preparedStatement.setString(6, company.getPhoneNumber());

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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    // Method to get all companies from the database
    public List<Company> getAllCompanies() {
        List<Company> companyList = new ArrayList<>();
        try {
            String query = "SELECT * FROM company";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Company company = new Company();
                company.setId(resultSet.getLong("id"));
                company.setName(resultSet.getString("name"));
                company.setDescription(resultSet.getString("description"));
                company.setEmail(resultSet.getString("email"));
                company.setWebsite(resultSet.getString("website"));
                company.setLocation(resultSet.getString("location"));
                company.setPhoneNumber(resultSet.getString("phone_number"));

                companyList.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }

    // Method to update an existing company in the database
    public void updateCompany(Company company) {
        try {
            String query = "UPDATE company SET name=?, description=?, email=?, website=?, location=?, phone_number=? " +
                    "WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getDescription());
            preparedStatement.setString(3, company.getEmail());
            preparedStatement.setString(4, company.getWebsite());
            preparedStatement.setString(5, company.getLocation());
            preparedStatement.setString(6, company.getPhoneNumber());
            preparedStatement.setLong(7, company.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a company from the database
    public void deleteCompany(Long companyId) {
        try {
            String query = "DELETE FROM company WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, companyId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
