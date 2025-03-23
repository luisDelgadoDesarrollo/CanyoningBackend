package es.luis.canyoningApp.application.rest.controller;

import es.luis.canyoningApp.canyoningApp.application.rest.api.ImageApi;
import es.luis.canyoningApp.domain.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController extends BaseController implements ImageApi {

  @Autowired private ImageService imageService;

  @Override
  public ResponseEntity<Resource> imagesImageDirGet(String image, String dir) {
    return ResponseEntity.ok(imageService.getImage(image, dir));
  }

  @Override
  public ResponseEntity<Void> postImage(MultipartFile file, String name, String dir) {
    imageService.postImage(file, name, dir);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
