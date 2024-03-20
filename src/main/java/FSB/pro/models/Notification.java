package FSB.pro.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Notification {
    private Long id;
    private String description;
    private Long fromUserId; // Corresponds to fromUser_id in the database
    private Long toUserId;   // Corresponds to toUser_id in the database
    private LocalDate date;
    private LocalDateTime time;

    // Getters and setters...

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Notification() {
    }
    public Notification(String description, Long fromUserId, Long toUserId, LocalDate date, LocalDateTime time) {
        this.description = description;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.date = date;
        this.time = time;
    }
    public Notification(Long id, String description, Long fromUserId, Long toUserId, LocalDate date, LocalDateTime time) {
        this.id = id;
        this.description = description;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.date = date;
        this.time = time;
    }

    
    
}