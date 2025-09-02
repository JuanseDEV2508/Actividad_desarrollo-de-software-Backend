package com.smartdevices.controller;

import com.smartdevices.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main controller for home page and general navigation
 * Part of the Presentation layer in layered architecture
 */
@Controller
public class HomeController {
    
    private final DeviceService deviceService;
    
    @Autowired
    public HomeController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    
    // Home page
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("latestDevices", deviceService.getLatestDevices());
        model.addAttribute("deviceTypes", com.smartdevices.model.DeviceType.values());
        model.addAttribute("brands", deviceService.getAllBrands());
        return "home";
    }
    
    // About page
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    
    // Contact page
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    
    // Admin dashboard
    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("totalDevices", deviceService.getAllDevices().size());
        model.addAttribute("latestDevices", deviceService.getLatestDevices());
        return "admin/dashboard";
    }
}
