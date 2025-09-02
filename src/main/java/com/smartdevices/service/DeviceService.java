package com.smartdevices.service;

import com.smartdevices.model.Device;
import com.smartdevices.model.DeviceType;
import com.smartdevices.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Device business logic
 * Part of the Business Logic layer in layered architecture
 */
@Service
@Transactional
public class DeviceService {
    
    private final DeviceRepository deviceRepository;
    
    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }
    
    // CRUD Operations
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
    
    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }
    
    public Device createDevice(Device device) {
        return deviceRepository.save(device);
    }
    
    public Device updateDevice(Device device) {
        if (!deviceRepository.existsById(device.getId())) {
            throw new RuntimeException("Device not found with id: " + device.getId());
        }
        return deviceRepository.save(device);
    }
    
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }
    
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }
    
    // Business Logic Methods
    public List<Device> getLatestDevices() {
        return deviceRepository.findTop10ByOrderByReleaseDateDesc();
    }
    
    public List<Device> getDevicesByType(DeviceType type) {
        return deviceRepository.findByType(type);
    }
    
    public List<Device> getDevicesByBrand(String brand) {
        return deviceRepository.findByBrandIgnoreCase(brand);
    }
    
    public List<Device> getDevicesByTypeAndBrand(DeviceType type, String brand) {
        return deviceRepository.findByTypeAndBrandIgnoreCase(type, brand);
    }
    
    public List<Device> getDevicesReleasedAfter(LocalDate date) {
        return deviceRepository.findByReleaseDateAfter(date);
    }
    
    public List<Device> getDevicesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return deviceRepository.findByPriceBetween(minPrice, maxPrice);
    }
    
    public List<Device> searchDevices(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllDevices();
        }
        return deviceRepository.searchDevices(searchTerm.trim());
    }
    
    public List<String> getAllBrands() {
        return deviceRepository.findAllBrands();
    }
    
    public List<Device> getDevicesByTypeOrderedByReleaseDate(DeviceType type) {
        return deviceRepository.findByTypeOrderByReleaseDateDesc(type);
    }
    
    // Validation methods
    public boolean deviceExists(Long id) {
        return deviceRepository.existsById(id);
    }
    
    public boolean isValidPrice(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0;
    }
    
    public boolean isValidReleaseDate(LocalDate releaseDate) {
        return releaseDate == null || !releaseDate.isAfter(LocalDate.now());
    }
}
