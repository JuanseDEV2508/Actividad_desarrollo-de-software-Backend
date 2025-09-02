package com.smartdevices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for Smart Devices Web Application
 * Implements layered architecture pattern
 */
@SpringBootApplication
public class SmartDevicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartDevicesApplication.class, args);
    }
}
