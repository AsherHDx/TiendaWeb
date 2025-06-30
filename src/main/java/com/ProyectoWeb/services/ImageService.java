package com.ProyectoWeb.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ImageService {
    public Map uploadImage(MultipartFile img) throws IOException;
    public Map deleteImage(String publicId) throws IOException;
}
