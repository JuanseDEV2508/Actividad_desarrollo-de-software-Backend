package com.smartdevices.repository;

import com.smartdevices.model.Device;
import com.smartdevices.model.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for Device entity
 * Part of the Data Access layer in layered architecture
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    
    // Find devices by type
    List<Device> findByType(DeviceType type);
    
    // Find devices by brand
    List<Device> findByBrandIgnoreCase(String brand);
    
    // Find devices by type and brand
    List<Device> findByTypeAndBrandIgnoreCase(DeviceType type, String brand);
    
    // Find devices released after a specific date
    List<Device> findByReleaseDateAfter(LocalDate date);
    
    // Find devices by price range
    List<Device> findByPriceBetween(java.math.BigDecimal minPrice, java.math.BigDecimal maxPrice);
    
    // Search devices by name or description
    @Query("SELECT d FROM Device d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(d.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(d.brand) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Device> searchDevices(@Param("searchTerm") String searchTerm);
    
    // Find latest devices (ordered by release date)
    List<Device> findTop10ByOrderByReleaseDateDesc();
    
    // Find devices by type ordered by release date
    List<Device> findByTypeOrderByReleaseDateDesc(DeviceType type);
    
    // Find all brands
    @Query("SELECT DISTINCT d.brand FROM Device d ORDER BY d.brand")
    List<String> findAllBrands();
}
