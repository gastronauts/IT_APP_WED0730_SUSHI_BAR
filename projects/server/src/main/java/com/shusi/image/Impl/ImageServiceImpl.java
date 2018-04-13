package com.shusi.image.Impl;

import com.shusi.image.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageServiceImpl implements ImageService {

    private final Path rootLocation = Paths.get("store");

    @Override
    public String store(MultipartFile image) {
        try {
            Files.copy(image.getInputStream(), this.rootLocation.resolve(image.getOriginalFilename()));
            return "/image/?filename=" + image.getOriginalFilename();
        } catch (Exception e) {
            throw new RuntimeException("Fail to save image");
        }
    }

    @Override
    public Resource loadImage(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Fail to load the image");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Fail to read the image");
        }
    }

    @Override
    public Boolean deleteAll() {
        return FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage");
        }
    }
}
