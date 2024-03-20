package FSB.pro.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private String text;
    private LocalDate sendDate;
    private LocalDateTime sendTime;
    private Long senderId;
    private Long postId;
    public Comment() {
    }
    public Comment(Long id, String text, LocalDate sendDate, LocalDateTime sendTime, Long senderId, Long postId) {
        this.id = id;
        this.text = text;
        this.sendDate = sendDate;
        this.sendTime = sendTime;
        this.senderId = senderId;
        this.postId = postId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public LocalDate getSendDate() {
        return sendDate;
    }
    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }
    public LocalDateTime getSendTime() {
        return sendTime;
    }
    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }
    public Long getSenderId() {
        return senderId;
    }
    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }
    public Long getPostId() {
        return postId;
    }
    public void setPostId(Long postId) {
        this.postId = postId;
    }
    
}
