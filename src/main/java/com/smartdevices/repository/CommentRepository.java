package com.smartdevices.repository;

import com.smartdevices.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Comment entity
 * Part of the Data Access layer in layered architecture
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    // Find comments by device ID
    List<Comment> findByDeviceIdOrderByCreatedAtDesc(Long deviceId);
    
    // Find comments by user name
    List<Comment> findByUserNameIgnoreCaseOrderByCreatedAtDesc(String userName);
    
    // Find comments with rating greater than or equal to
    List<Comment> findByRatingGreaterThanEqual(Integer rating);
    
    // Find comments by device ID and rating
    List<Comment> findByDeviceIdAndRatingOrderByCreatedAtDesc(Long deviceId, Integer rating);
    
    // Get average rating for a device
    @Query("SELECT AVG(c.rating) FROM Comment c WHERE c.device.id = :deviceId")
    Double getAverageRatingByDeviceId(@Param("deviceId") Long deviceId);
    
    // Get comment count for a device
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.device.id = :deviceId")
    Long getCommentCountByDeviceId(@Param("deviceId") Long deviceId);
}
