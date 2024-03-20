package FSB.pro.models;

import java.util.List;

public class User {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String bio;
    private String photo; // Add this field for the user's photo
    private List<User> friends;
    private int friendsCount;
    private int msgCount;
    private int notCount;
    private String number; 
    private String role;
    private String matchingPassword;
    private boolean logged;

    public User() {
    }
    public User(String username,String firstname , String lastname, String email, String password, String phoneNumber, String role) {
        this.username = username;
        this.email = email;
        this.firstName = firstname;
        this.lastName = lastname;
        this.password = password;
        this.number = phoneNumber;
        this.role = role;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhoneNumber() {
        return number;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.number = phoneNumber;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public int getNotCount() {
        return notCount;
    }

    public void setNotCount(int notCount) {
        this.notCount = notCount;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }


    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
