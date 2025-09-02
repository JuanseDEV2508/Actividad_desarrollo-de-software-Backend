package com.smartdevices.service;

import com.smartdevices.model.Comment;
import com.smartdevices.model.Device;
import com.smartdevices.repository.CommentRepository;
import com.smartdevices.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Comment business logic
 * Part of the Business Logic layer in layered architecture
 */
@Service
@Transactional
public class CommentService {
    
    private final CommentRepository commentRepository;
    private final DeviceRepository deviceRepository;
    
    @Autowired
    public CommentService(CommentRepository commentRepository, DeviceRepository deviceRepository) {
        this.commentRepository = commentRepository;
        this.deviceRepository = deviceRepository;
    }
    
    // CRUD Operations
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }
    
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
    
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
    
    // Business Logic Methods
    public List<Comment> getCommentsByDeviceId(Long deviceId) {
        return commentRepository.findByDeviceIdOrderByCreatedAtDesc(deviceId);
    }
    
    public List<Comment> getCommentsByUserName(String userName) {
        return commentRepository.findByUserNameIgnoreCaseOrderByCreatedAtDesc(userName);
    }
    
    public List<Comment> getCommentsByRating(Integer rating) {
        return commentRepository.findByRatingGreaterThanEqual(rating);
    }
    
    public List<Comment> getCommentsByDeviceIdAndRating(Long deviceId, Integer rating) {
        return commentRepository.findByDeviceIdAndRatingOrderByCreatedAtDesc(deviceId, rating);
    }
    
    public Double getAverageRatingByDeviceId(Long deviceId) {
        return commentRepository.getAverageRatingByDeviceId(deviceId);
    }
    
    public Long getCommentCountByDeviceId(Long deviceId) {
        return commentRepository.getCommentCountByDeviceId(deviceId);
    }
    
    // Add comment to device
    public Comment addCommentToDevice(Long deviceId, Comment comment) {
        Optional<Device> deviceOpt = deviceRepository.findById(deviceId);
        if (deviceOpt.isPresent()) {
            Device device = deviceOpt.get();
            comment.setDevice(device);
            return commentRepository.save(comment);
        }
        throw new IllegalArgumentException("Device not found with id: " + deviceId);
    }
    
    // Validation methods
    public boolean isValidRating(Integer rating) {
        return rating != null && rating >= 1 && rating <= 5;
    }
    
    public boolean isValidCommentText(String commentText) {
        return commentText != null && commentText.trim().length() >= 10 && commentText.trim().length() <= 500;
    }
    
    public boolean isValidUserName(String userName) {
        return userName != null && !userName.trim().isEmpty() && userName.trim().length() <= 100;
    }
    
    public boolean commentExists(Long id) {
        return commentRepository.existsById(id);
    }
}
