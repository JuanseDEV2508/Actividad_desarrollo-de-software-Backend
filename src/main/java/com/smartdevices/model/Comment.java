package com.smartdevices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Entity representing a user comment on a device
 * Part of the Model layer in layered architecture
 */
@Entity
@Table(name = "comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "User name is required")
    @Column(name = "user_name", nullable = false)
    private String userName;
    
    @NotBlank(message = "Comment text is required")
    @Size(min = 10, max = 500, message = "Comment must be between 10 and 500 characters")
    @Column(name = "comment_text", nullable = false, length = 500)
    private String commentText;
    
    @NotNull(message = "Rating is required")
    @Column(nullable = false)
    private Integer rating; // 1-5 stars
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    @JsonBackReference
    private Device device;
    
    // Constructors
    public Comment() {
        this.createdAt = LocalDateTime.now();
    }
    
    public Comment(String userName, String commentText, Integer rating) {
        this();
        this.userName = userName;
        this.commentText = commentText;
        this.rating = rating;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    
    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }
    
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public Device getDevice() { return device; }
    public void setDevice(Device device) { this.device = device; }
}
