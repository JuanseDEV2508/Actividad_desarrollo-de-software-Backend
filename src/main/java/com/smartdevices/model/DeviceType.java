package com.smartdevices.model;

/**
 * Enum representing different types of smart devices
 */
public enum DeviceType {
    SMARTPHONE("Smartphone"),
    LAPTOP("Laptop"),
    TABLET("Tablet"),
    SMARTWATCH("Smartwatch"),
    HEADPHONES("Headphones"),
    SMART_SPEAKER("Smart Speaker"),
    CAMERA("Camera"),
    GAMING_CONSOLE("Gaming Console");
    
    private final String displayName;
    
    DeviceType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
