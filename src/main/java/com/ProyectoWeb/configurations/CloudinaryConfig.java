package com.ProyectoWeb.configurations;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dw5txqjkn");
        config.put("api_key", "687775998511158");
        config.put("api_secret", "01qFQQviO-CloK3IBPuwGOrn8Rc");
        return new Cloudinary(config);
    }
}
