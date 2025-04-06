package es.luis.canyoningApp.domain.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void postImage(MultipartFile file, String name, String dir);

    Resource getImage(String image, String dir);
}
