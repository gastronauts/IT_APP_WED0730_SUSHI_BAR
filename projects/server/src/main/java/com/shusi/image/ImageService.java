package com.shusi.image;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {

    String store(MultipartFile image);

    Resource loadImage(String filename);

    Boolean deleteAll();

    void init();
}
