package org.skypro.skyshop;

import org.skypro.skyshop.service.StorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public StorageService storageService() {
        return new StorageService();
    }
}
