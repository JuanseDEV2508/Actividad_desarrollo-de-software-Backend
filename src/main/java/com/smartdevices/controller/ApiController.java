package com.smartdevices.controller;

import com.smartdevices.model.Device;
import com.smartdevices.model.DeviceType;
import com.smartdevices.service.DeviceService;
import com.smartdevices.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ApiController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private CommentService commentService;

    // Device endpoints
    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping("/devices/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        return deviceService.getDeviceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/devices")
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        return ResponseEntity.ok(deviceService.createDevice(device));
    }

    @PutMapping("/devices/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody Device device) {
        device.setId(id);
        return ResponseEntity.ok(deviceService.updateDevice(device));
    }

    @DeleteMapping("/devices/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/devices/type/{type}")
    public ResponseEntity<List<Device>> getDevicesByType(@PathVariable DeviceType type) {
        return ResponseEntity.ok(deviceService.getDevicesByType(type));
    }

    @GetMapping("/devices/brand/{brand}")
    public ResponseEntity<List<Device>> getDevicesByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(deviceService.getDevicesByBrand(brand));
    }

    @GetMapping("/devices/search")
    public ResponseEntity<List<Device>> searchDevices(@RequestParam String q) {
        return ResponseEntity.ok(deviceService.searchDevices(q));
    }

    @GetMapping("/devices/latest")
    public ResponseEntity<List<Device>> getLatestDevices() {
        return ResponseEntity.ok(deviceService.getLatestDevices());
    }

    @GetMapping("/devices/brands")
    public ResponseEntity<List<String>> getAllBrands() {
        return ResponseEntity.ok(deviceService.getAllBrands());
    }

    // Comment endpoints
    @GetMapping("/comments/device/{deviceId}")
    public ResponseEntity<?> getCommentsByDeviceId(@PathVariable Long deviceId) {
        return ResponseEntity.ok(commentService.getCommentsByDeviceId(deviceId));
    }

    @PostMapping("/comments/device/{deviceId}")
    public ResponseEntity<?> addComment(@PathVariable Long deviceId, @RequestBody com.smartdevices.model.Comment comment) {
        return ResponseEntity.ok(commentService.addCommentToDevice(deviceId, comment));
    }

    @GetMapping("/comments/device/{deviceId}/average-rating")
    public ResponseEntity<Double> getAverageRatingByDeviceId(@PathVariable Long deviceId) {
        return ResponseEntity.ok(commentService.getAverageRatingByDeviceId(deviceId));
    }

    @GetMapping("/comments/device/{deviceId}/count")
    public ResponseEntity<Long> getCommentCountByDeviceId(@PathVariable Long deviceId) {
        return ResponseEntity.ok(commentService.getCommentCountByDeviceId(deviceId));
    }
}
