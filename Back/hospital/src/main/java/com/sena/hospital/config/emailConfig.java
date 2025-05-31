package com.sena.hospital.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class emailConfig {
    private boolean enabled = true;

    // Getters y setters
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}