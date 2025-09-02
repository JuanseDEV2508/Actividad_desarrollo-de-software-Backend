package com.smartdevices.controller;

import com.smartdevices.model.Comment;
import com.smartdevices.service.CommentService;
import com.smartdevices.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for Comment-related operations
 * Part of the Presentation layer in layered architecture
 */
@Controller
@RequestMapping("/comments")
public class CommentController {
    
    private final CommentService commentService;
    private final DeviceService deviceService;
    
    @Autowired
    public CommentController(CommentService commentService, DeviceService deviceService) {
        this.commentService = commentService;
        this.deviceService = deviceService;
    }
    
    // Add comment to device
    @PostMapping("/device/{deviceId}")
    public String addComment(@PathVariable Long deviceId,
                            @Valid @ModelAttribute Comment comment,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Please check your comment data");
            return "redirect:/devices/" + deviceId;
        }
        
        // Validate comment data
        if (!commentService.isValidRating(comment.getRating())) {
            redirectAttributes.addFlashAttribute("error", "Rating must be between 1 and 5");
            return "redirect:/devices/" + deviceId;
        }
        
        if (!commentService.isValidCommentText(comment.getCommentText())) {
            redirectAttributes.addFlashAttribute("error", "Comment must be between 10 and 500 characters");
            return "redirect:/devices/" + deviceId;
        }
        
        if (!commentService.isValidUserName(comment.getUserName())) {
            redirectAttributes.addFlashAttribute("error", "Please provide a valid user name");
            return "redirect:/devices/" + deviceId;
        }
        
        try {
            commentService.addCommentToDevice(deviceId, comment);
            redirectAttributes.addFlashAttribute("success", "Comment added successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Device not found");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding comment: " + e.getMessage());
        }
        
        return "redirect:/devices/" + deviceId;
    }
    
    // Admin: List all comments
    @GetMapping("/admin")
    public String listAllComments(Model model) {
        model.addAttribute("comments", commentService.getAllComments());
        return "admin/comments-list";
    }
    
    // Admin: Delete comment
    @PostMapping("/admin/delete/{id}")
    public String deleteComment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            commentService.deleteComment(id);
            redirectAttributes.addFlashAttribute("success", "Comment deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting comment: " + e.getMessage());
        }
        return "redirect:/comments/admin";
    }
    
    // Admin: Show comment details
    @GetMapping("/admin/{id}")
    public String showCommentDetail(@PathVariable Long id, Model model) {
        return commentService.getCommentById(id)
                .map(comment -> {
                    model.addAttribute("comment", comment);
                    return "admin/comment-detail";
                })
                .orElse("redirect:/comments/admin");
    }
}
