package FSB.pro.models;

import java.time.LocalDateTime;

public class Messages {
    private Long id;
    private String text;
    private LocalDateTime sendDate;
    private User sender;
    private User addressee;
    private byte[] voiceMsg; // For storing voice messages
    private String picture; // For storing picture path/URL

    public Messages() {
    }
    public Messages(String text, LocalDateTime sendDate, User sender, User addressee) {
        this.text = text;
        this.sendDate = sendDate;
        this.sender = sender;
        this.addressee = addressee;
    }
    public Messages(String text, LocalDateTime sendDate, User sender, User addressee, byte[] voiceMsg) {
        this.text = text;
        this.sendDate = sendDate;
        this.sender = sender;
        this.addressee = addressee;
        this.voiceMsg = voiceMsg;
    }
    public Messages(String text, LocalDateTime sendDate, User sender, User addressee, String picture) {
        this.text = text;
        this.sendDate = sendDate;
        this.sender = sender;
        this.addressee = addressee;
        this.picture = picture;
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
    public LocalDateTime getSendDate() {
        return sendDate;
    }
    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }
    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
    public User getAddressee() {
        return addressee;
    }
    public void setAddressee(User addressee) {
        this.addressee = addressee;
    }
    public byte[] getVoiceMsg() {
        return voiceMsg;
    }
    public void setVoiceMsg(byte[] voiceMsg) {
        this.voiceMsg = voiceMsg;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
}


