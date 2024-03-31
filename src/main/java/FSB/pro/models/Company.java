package FSB.pro.models;

import java.util.List;

public class Company {
    private Long id;
    private String logo;
    private String name;
    private String description;
    private String email;
    private String website;
    private String location;
    private String phoneNumber;
    private String password;
    private List<String> services; // List of services offered by the company
    private List<String> teamMembers; // List of team members
    private List<String> projects; // List of projects
    private int likes; // Number of likes
    // Additional fields related to company profile
    public Company(String name, String description, String email, String website, String location, String phoneNumber,String password) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.website = website;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.services = services;
        this.teamMembers = teamMembers;
        this.projects = projects;
        this.likes = likes;
        this.logo = logo;
    }
    public Company() {
    }

    // Getters and setters for the new fields

    public List<String> getServices() {
        return services;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<String> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<String> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    // Getters and setters for the existing fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
