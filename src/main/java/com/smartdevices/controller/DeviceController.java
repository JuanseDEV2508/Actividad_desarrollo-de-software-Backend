package com.smartdevices.controller;

import com.smartdevices.model.Device;
import com.smartdevices.model.DeviceType;
import com.smartdevices.service.DeviceService;
import com.smartdevices.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller for Device-related operations
 * Part of the Presentation layer in layered architecture
 */
@Controller
@RequestMapping("/devices")
public class DeviceController {
    
    private final DeviceService deviceService;
    private final CommentService commentService;
    
    @Autowired
    public DeviceController(DeviceService deviceService, CommentService commentService) {
        this.deviceService = deviceService;
        this.commentService = commentService;
    }
    
    // Home page - List all devices
    @GetMapping
    public String listDevices(Model model) {
        List<Device> devices = deviceService.getLatestDevices();
        List<String> brands = deviceService.getAllBrands();
        
        model.addAttribute("devices", devices);
        model.addAttribute("brands", brands);
        model.addAttribute("deviceTypes", DeviceType.values());
        return "devices/list";
    }
    
    // Device detail page
    @GetMapping("/{id}")
    public String deviceDetail(@PathVariable Long id, Model model) {
        return deviceService.getDeviceById(id)
                .map(device -> {
                    model.addAttribute("device", device);
                    model.addAttribute("comments", commentService.getCommentsByDeviceId(id));
                    model.addAttribute("averageRating", commentService.getAverageRatingByDeviceId(id));
                    model.addAttribute("commentCount", commentService.getCommentCountByDeviceId(id));
                    return "devices/detail";
                })
                .orElse("redirect:/devices");
    }
    
    // Search devices
    @GetMapping("/search")
    public String searchDevices(@RequestParam(required = false) String q, Model model) {
        List<Device> devices = deviceService.searchDevices(q);
        model.addAttribute("devices", devices);
        model.addAttribute("searchTerm", q);
        model.addAttribute("deviceTypes", DeviceType.values());
        model.addAttribute("brands", deviceService.getAllBrands());
        return "devices/search";
    }
    
    // Filter devices by type
    @GetMapping("/type/{type}")
    public String devicesByType(@PathVariable DeviceType type, Model model) {
        List<Device> devices = deviceService.getDevicesByTypeOrderedByReleaseDate(type);
        model.addAttribute("devices", devices);
        model.addAttribute("selectedType", type);
        model.addAttribute("deviceTypes", DeviceType.values());
        model.addAttribute("brands", deviceService.getAllBrands());
        return "devices/list";
    }
    
    // Filter devices by brand
    @GetMapping("/brand/{brand}")
    public String devicesByBrand(@PathVariable String brand, Model model) {
        List<Device> devices = deviceService.getDevicesByBrand(brand);
        model.addAttribute("devices", devices);
        model.addAttribute("selectedBrand", brand);
        model.addAttribute("deviceTypes", DeviceType.values());
        model.addAttribute("brands", deviceService.getAllBrands());
        return "devices/list";
    }
    
    // Advanced search with filters
    @GetMapping("/advanced-search")
    public String advancedSearch(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) DeviceType type,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            Model model) {
        
        List<Device> devices = deviceService.searchDevices(searchTerm);
        
        // Apply additional filters
        if (type != null) {
            devices = devices.stream().filter(d -> d.getType() == type).toList();
        }
        if (brand != null && !brand.isEmpty()) {
            devices = devices.stream().filter(d -> d.getBrand().equalsIgnoreCase(brand)).toList();
        }
        if (minPrice != null) {
            devices = devices.stream().filter(d -> d.getPrice().compareTo(minPrice) >= 0).toList();
        }
        if (maxPrice != null) {
            devices = devices.stream().filter(d -> d.getPrice().compareTo(maxPrice) <= 0).toList();
        }
        
        model.addAttribute("devices", devices);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("selectedType", type);
        model.addAttribute("selectedBrand", brand);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("deviceTypes", DeviceType.values());
        model.addAttribute("brands", deviceService.getAllBrands());
        
        return "devices/advanced-search";
    }
    
    // Admin: Show form to add new device
    @GetMapping("/admin/new")
    public String showAddDeviceForm(Model model) {
        model.addAttribute("device", new Device());
        model.addAttribute("deviceTypes", DeviceType.values());
        return "admin/device-form";
    }
    
    // Admin: Process add device form
    @PostMapping("/admin/new")
    public String addDevice(@Valid @ModelAttribute Device device, 
                           BindingResult result, 
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/device-form";
        }
        
        try {
            deviceService.saveDevice(device);
            redirectAttributes.addFlashAttribute("success", "Device added successfully!");
            return "redirect:/devices";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding device: " + e.getMessage());
            return "redirect:/devices/admin/new";
        }
    }
    
    // Admin: Show form to edit device
    @GetMapping("/admin/edit/{id}")
    public String showEditDeviceForm(@PathVariable Long id, Model model) {
        return deviceService.getDeviceById(id)
                .map(device -> {
                    model.addAttribute("device", device);
                    model.addAttribute("deviceTypes", DeviceType.values());
                    return "admin/device-form";
                })
                .orElse("redirect:/devices");
    }
    
    // Admin: Process edit device form
    @PostMapping("/admin/edit/{id}")
    public String editDevice(@PathVariable Long id,
                            @Valid @ModelAttribute Device device,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/device-form";
        }
        
        try {
            device.setId(id);
            deviceService.saveDevice(device);
            redirectAttributes.addFlashAttribute("success", "Device updated successfully!");
            return "redirect:/devices/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating device: " + e.getMessage());
            return "redirect:/devices/admin/edit/" + id;
        }
    }
    
    // Admin: Delete device
    @PostMapping("/admin/delete/{id}")
    public String deleteDevice(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            deviceService.deleteDevice(id);
            redirectAttributes.addFlashAttribute("success", "Device deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting device: " + e.getMessage());
        }
        return "redirect:/devices";
    }
}
