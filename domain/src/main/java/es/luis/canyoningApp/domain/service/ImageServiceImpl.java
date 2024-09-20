package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.common.CanyoningAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private CanyoningAppConfiguration canyoningAppConfiguration;

    @Override
    public void postImage(MultipartFile file, String name, String dir) {
        try {
            File directory = new File(canyoningAppConfiguration.getBasePath() + dir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!name.endsWith(".png")) name += ".png";

            Path filePath = Paths.get(directory.getPath() + name.replace(" ", "_"));
            Files.write(filePath, file.getBytes());

        } catch (Exception e) {
            new Throwable(e);
        }
    }

    @Override
    public Resource getImage(String image, String dir) {
        File directory = new File(canyoningAppConfiguration.getBasePath() + dir);
        File imageFile = new File(directory, image + ".png");
        if (imageFile.exists() && imageFile.isFile()) {
            // Devuelve el Resource usando FileSystemResource
            return new FileSystemResource(imageFile);
        } else {
            // Maneja el caso en que la imagen no exista
            return null; // O podrías lanzar una excepción personalizada
        }
    }
}
