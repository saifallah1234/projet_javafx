package FSB.pro.models;

import java.sql.Date;

public class JobOffer {
    private Long id;
    private Long companyId;
    private String title;
    private String description;
    private float salary;
    private String experience;
    private String location;
    private Date postedDate;
    private Date deadline;
    public JobOffer(String title, String description, float salary, String experience, String location) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.experience = experience;
        this.location = location;
        
    }
    public JobOffer() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void getExperience(String experience) {
        this.experience = experience;
    }
    public String setExperience() {
        return experience;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
