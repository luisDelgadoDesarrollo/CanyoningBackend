package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.common.CanyoningAppConfiguration;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

  @Autowired private CanyoningAppConfiguration canyoningAppConfiguration;

  @Autowired SendEmail sendEmail;

  @Override
  public void postImage(MultipartFile file, String name, String dir) {
    try {
      File directory =
          new File(
              canyoningAppConfiguration.getBasePath() + canyoningAppConfiguration.getSlash() + dir);
      if (!directory.exists()) {
        directory.mkdirs();
      }
      if (!name.endsWith(".jpg")) {
        String[] parts = name.split("\\.");
        name = String.join(".", Arrays.copyOfRange(parts, 0, parts.length)) + ".jpg";
      }

      Path filePath =
          Paths.get(
              directory.getPath() + canyoningAppConfiguration.getSlash() + name.replace(" ", "_"));
      Files.write(filePath, file.getBytes());

      sendEmail.sendImageChange(filePath.toString());
    } catch (Exception e) {
      new Throwable(e);
    }
  }

  @Override
  public Resource getImage(String image, String dir) {
    File directory =
        new File(
            canyoningAppConfiguration.getBasePath() + canyoningAppConfiguration.getSlash() + dir);
    File imageFile = new File(directory, image);
    if (imageFile.exists() && imageFile.isFile()) {
      // Devuelve el Resource usando FileSystemResource
      return new FileSystemResource(imageFile);
    } else {
      // Maneja el caso en que la imagen no exista
      return null; // O podrías lanzar una excepción personalizada
    }
  }
}
