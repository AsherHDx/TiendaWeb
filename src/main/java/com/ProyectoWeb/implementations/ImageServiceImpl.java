package com.ProyectoWeb.implementations;

import com.ProyectoWeb.services.ImageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private Cloudinary cloudinary;


    @Override
    public Map uploadImage(MultipartFile img) throws IOException {
        Map<String, Object> options = ObjectUtils.asMap(
                "folder", "ProyectoWeb/productos",
                "overwrite", true
        );
        return cloudinary.uploader().upload(img.getBytes(), options);
    }

    @Override
    public Map deleteImage(String publicId) throws IOException {
        return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}
