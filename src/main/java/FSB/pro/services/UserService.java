package FSB.pro.services;
import FSB.pro.models.User;

import java.util.List;

import FSB.pro.DAO.*;

public class UserService {
    
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean signupUser(String username, String firstname, String lastname, String email, String password, String confirmPassword, String phoneNumber, String role) {
        if (isUsernameUnique(username) && isEmailUnique(email)) {
            User newUser = new User(username, email, firstname, lastname, password, phoneNumber, role);
            userDAO.addUser(newUser);
            return true;  // Signup successful
        } else {
            return false; // Username or email is not unique
        }
    }

    private boolean isUsernameUnique(String username) {
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            if (user.getUsername()!=null && user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmailUnique(String email) {
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
}
