package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.common.CanyoningAppConfiguration;
import es.luis.canyoningApp.domain.exception.BadRequestException;
import es.luis.canyoningApp.domain.model.Canyon;
import es.luis.canyoningApp.domain.model.CanyonControlLevel;
import es.luis.canyoningApp.domain.model.CanyonRappeling;
import es.luis.canyoningApp.domain.model.SimpleCanyon;
import es.luis.canyoningApp.domain.repository.CanyonRepository;
import jakarta.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.java.Log;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Log
public class CanyonServiceIpml implements CanyonService {

  private static final float MARGIN = 50;
  private static final float PAGE_WIDTH = 500;
  @Autowired private CanyonRepository canyonRepository;

  @Autowired private SendEmail sendEmail;

  @Autowired private CanyoningAppConfiguration canyoningAppConfiguration;

  @Override
  public Canyon createCanyon(Canyon canyon) {
    if (ObjectUtils.isEmpty(canyon.getName())) {
      throw new BadRequestException(
          "Campo faltante", new Throwable("Falta el nombre del barranco"));
    }
    canyon.setCroquis(canyon.getCroquis().replace(" ", "_") + "_map.png");
    return canyonRepository.createCanyon(canyon);
  }

  @Override
  public Canyon getCanyonById(Long canyonId) {
    Canyon canyon = canyonRepository.getCanyonById(canyonId);

    // Asegúrate de que canyonRappeling no sea null
    List<CanyonRappeling> rappelingList = canyon.getCanyonRappeling();
    if (rappelingList != null) {
      rappelingList.sort(
          (o1, o2) -> {
            String step1 = o1.getStep();
            String step2 = o2.getStep();
            // Si step está vacío, asignar "0"
            if (step1 == null || step1.isEmpty()) {
              step1 = "0";
            }
            if (step2 == null || step2.isEmpty()) {
              step2 = "0";
            }

            // Extraer el número y el sufijo para step1
            String[] parts1 = step1.split("(?<=\\d)(?=\\D)"); // Divide en número y letra
            Integer num1 = Integer.valueOf(parts1[0]);
            String suffix1 = parts1.length > 1 ? parts1[1] : ""; // Sufijo puede ser vacío

            // Extraer el número y el sufijo para step2
            String[] parts2 = step2.split("(?<=\\d)(?=\\D)"); // Divide en número y letra
            Integer num2 = Integer.valueOf(parts2[0]);
            String suffix2 = parts2.length > 1 ? parts2[1] : ""; // Sufijo puede ser vacío

            // Comparar primero por número
            int numberComparison = num1.compareTo(num2);
            if (numberComparison != 0) {
              return numberComparison;
            }

            // Si los números son iguales, comparar por sufijo
            return suffix1.compareTo(suffix2);
          });
    }

    canyon.setCanyonRappeling(rappelingList);
    return canyon;
  }

  @Override
  public Page<SimpleCanyon> getCanyons(
      String name, String season, String river, String population, Pageable pageable) {
    return canyonRepository.getCanyons(name, season, river, population, pageable);
  }

  @Override
  public void deleteCanyon(Long canyonId) {
    SimpleCanyon simpleCanyon = getCanyonById(canyonId);
    simpleCanyon.setDeleteAt(OffsetDateTime.now());
    canyonRepository.deleteCanyon(simpleCanyon);
  }

  @Override
  public Canyon updateCanyon(Long canyonId, Canyon canyon) {
    Canyon canyonSaved = getCanyonById(canyonId);
    canyon.setCanyonId(canyonSaved.getCanyonId());
    canyon.setName(canyonSaved.getName());
    canyon.setCroquis(canyon.getCroquis().trim().replace(" ", "_"));
    return canyonRepository.updateCanyon(canyon);
  }

  @Override
  public Resource downloadCanyon(Long canyonId, Boolean email) {
    Canyon canyon = canyonRepository.getCanyonById(canyonId);
    try (PDDocument document = new PDDocument()) {
      PDPage page = new PDPage();
      document.addPage(page);

      // anado informacion general sobre el barranco
      try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 750);
        addText(contentStream, canyon.getName(), "title");

        if (!ObjectUtils.isEmpty(canyon.getCanyonProhibition())) {
          addText(contentStream, "Prohibiciones:", "secondTitle");
          canyon
              .getCanyonProhibition()
              .forEach(
                  canyonProhibition -> {
                    try {
                      addText(contentStream, canyonProhibition.getDescription(), "text");
                    } catch (IOException e) {
                      throw new RuntimeException(e);
                    }
                  });
        }
        if (!ObjectUtils.isEmpty(canyon.getSeason())) {
          addText(contentStream, "Temprada ideal:", "secondTitle");
          addText(contentStream, canyon.getSeason(), "text");
        }
        if (!ObjectUtils.isEmpty(canyon.getDescription())) {
          addText(contentStream, "Descripcion:", "secondTitle");
          addText(contentStream, canyon.getDescription(), "text");
        }
        if (!ObjectUtils.isEmpty(canyon.getRiver())) {
          addText(contentStream, "Rio:", "secondTitle");
          addText(contentStream, canyon.getRiver(), "text");
        }
        if (!ObjectUtils.isEmpty(canyon.getPopulation())) {
          addText(contentStream, "Poblacion:", "secondTitle");
          addText(contentStream, canyon.getPopulation(), "text");
        }
        if (!ObjectUtils.isEmpty(canyon.getAccess())) {
          addText(contentStream, "Acceso:", "secondTitle");
          addText(contentStream, canyon.getAccess(), "text");
        }
        if (!ObjectUtils.isEmpty(canyon.getApproach())) {
          addText(contentStream, "Aproximacion:", "secondTitle");
          addText(contentStream, canyon.getApproach(), "text");
        }
        if (!ObjectUtils.isEmpty(canyon.getDescent())) {
          addText(contentStream, "Descenso:", "secondTitle");
          addText(contentStream, canyon.getDescent(), "text");
        }
        if (!ObjectUtils.isEmpty(canyon.get_return())) {
          addText(contentStream, "Retorno:", "secondTitle");
          addText(contentStream, canyon.get_return(), "text");
        }
        if (!ObjectUtils.isEmpty(canyon.getScape())) {
          addText(contentStream, "Escape:", "secondTitle");
          addText(contentStream, canyon.getScape(), "text");
        }

        if (!ObjectUtils.isEmpty(canyon.getCanyonDifficulty())) {
          addText(contentStream, "Dificultad:", "secondTitle");
          canyon
              .getCanyonDifficulty()
              .forEach(
                  canyonDifficulty -> {
                    try {
                      addText(
                          contentStream,
                          "Descenso "
                              + canyonDifficulty.getDescentNumber()
                              + ":"
                              + canyonDifficulty.getDifficultyDesc(),
                          "text");
                    } catch (IOException e) {
                      throw new RuntimeException(e);
                    }
                  });
        }

        if (!ObjectUtils.isEmpty(canyon.getCanyonLink())) {
          addText(contentStream, "Links:", "secondTitle");
          canyon
              .getCanyonLink()
              .forEach(
                  canyonLink -> {
                    try {
                      addText(
                          contentStream,
                          canyonLink.getTitle() + ":" + canyonLink.getLink(),
                          "text");
                    } catch (IOException e) {
                      throw new RuntimeException(e);
                    }
                  });
        }

        if (!ObjectUtils.isEmpty(canyon.getCanyonDescent())) {
          addText(contentStream, "Descensos:", "secondTitle");
          canyon
              .getCanyonDescent()
              .forEach(
                  canyonDescent -> {
                    try {

                      addText(
                          contentStream,
                          "Descenso "
                              + canyonDescent.getDescentNumber()
                              + ": Longitud = "
                              + canyonDescent.getLength()
                              + ", Desnivel = "
                              + canyonDescent.getSlope()
                              + ", Numero de rapeles: "
                              + canyonDescent.getRapelNum()
                              + ", Longitud maxima en un rapel = "
                              + canyonDescent.getMaxLength()
                              + ", Estado del equipamiento = "
                              + canyonDescent.getEquipment(),
                          "text");
                    } catch (IOException e) {
                      throw new RuntimeException(e);
                    }
                  });
        }

        if (!ObjectUtils.isEmpty(canyon.getCanyonSchedule())) {
          addText(contentStream, "Horarios:", "secondTitle");
          canyon
              .getCanyonSchedule()
              .forEach(
                  canyonSchedule -> {
                    try {

                      addText(
                          contentStream,
                          "Coches: "
                              + canyonSchedule.getCar()
                              + ", Numero de descenso: "
                              + canyonSchedule.getDescentNumber()
                              + ", Aproximacion = "
                              + canyonSchedule.getApproach()
                              + ", Descenso = "
                              + canyonSchedule.getDescent()
                              + ", Retorno = "
                              + canyonSchedule.get_return(),
                          "text");
                    } catch (IOException e) {
                      throw new RuntimeException(e);
                    }
                  });
        }

        contentStream.endText();
      }

      PDPage page2 = new PDPage();
      document.addPage(page2);

      // anado la informacion sobre los rapeles
      try (PDPageContentStream contentStream = new PDPageContentStream(document, page2)) {
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 750);
        addText(contentStream, "Repeles y pasos", "title");

        if (!ObjectUtils.isEmpty(canyon.getCanyonRappeling())) {
          final Integer[] descent = {0};
          canyon.getCanyonRappeling().stream()
              .sorted((c1, c2) -> compareSteps(c1.getStep(), c2.getStep()))
              .forEach(
                  canyonRappeling -> {
                    try {
                      if (!descent[0].equals(canyonRappeling.getDescentNumber())) {
                        descent[0] = canyonRappeling.getDescentNumber();
                        addText(
                            contentStream,
                            "Descenso: " + canyonRappeling.getDescentNumber(),
                            "secondTitle");
                      }
                      addText(
                          contentStream,
                          canyonRappeling.getStep()
                              + "   |   "
                              + canyonRappeling.getStepType()
                              + "   |   "
                              + canyonRappeling.getLength()
                              + "   |   "
                              + canyonRappeling.getLocation()
                              + "   |   "
                              + canyonRappeling.getDescription(),
                          "text");
                      contentStream.newLineAtOffset(0, -5);

                    } catch (IOException e) {
                      throw new RuntimeException(e);
                    }
                  });
        }

        contentStream.endText();
      }

      PDPage page3 = new PDPage();

      String baseImgPath =
          canyoningAppConfiguration.getBasePath() + canyoningAppConfiguration.getCroquis();
      String pathname = baseImgPath + canyon.getCroquis();
      log.info(pathname);
      File imageFile = new File(pathname);
      if (imageFile.exists()) {
        document.addPage(page3);
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page3)) {
          contentStream.beginText();
          contentStream.newLineAtOffset(50, 750);
          addText(contentStream, "Croquis", "title");
          contentStream.endText();
          // todo change to var enviroment
          PDImageXObject image = PDImageXObject.createFromFile(pathname, document);
          // Obtener el tamaño de la página
          float pageWidth = page.getMediaBox().getWidth();
          float pageHeight = page.getMediaBox().getHeight();

          // Obtener el tamaño de la imagen
          float imageWidth = image.getWidth();
          float imageHeight = image.getHeight();

          // Calcular la escala para ajustar la imagen al tamaño de la página
          float scale = Math.min(pageWidth / imageWidth, pageHeight / imageHeight);

          // Calcular las nuevas dimensiones de la imagen
          float scaledWidth = imageWidth * scale;
          float scaledHeight = imageHeight * scale;

          if (scaledHeight == pageHeight) {
            scaledWidth *= 0.7F;
            scaledHeight *= 0.7F;
          }
          // Calcular la posición de la imagen centrada en la página
          float x = (pageWidth - scaledWidth) / 2;
          float y = (pageHeight - scaledHeight) / 2;

          // Dibujar la imagen escalada y centrada en la página
          contentStream.drawImage(image, x, y, scaledWidth, scaledHeight);
        }
      }

      List<CanyonControlLevel> canyonControlLevel = canyon.getCanyonControlLevel();
      for (CanyonControlLevel controlLevel : canyonControlLevel) {
        PDPage pageExtra = new PDPage();
        baseImgPath =
            canyoningAppConfiguration.getBasePath() + canyoningAppConfiguration.getControlPoint();
        pathname = baseImgPath + controlLevel.getName();
        imageFile = new File(pathname);
        if (imageFile.exists()) {
          document.addPage(pageExtra);
          try (PDPageContentStream contentStream = new PDPageContentStream(document, pageExtra)) {
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);
            addText(contentStream, "Punto de control " + controlLevel.getControlPoint(), "title");
            addText(contentStream, "Nivel del caudal " + controlLevel.getCaudalLevel(), "title");
            contentStream.endText();
            // todo change to var enviroment
            PDImageXObject image = PDImageXObject.createFromFile(pathname, document);
            // Obtener el tamaño de la página
            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();

            // Obtener el tamaño de la imagen
            float imageWidth = image.getWidth();
            float imageHeight = image.getHeight();

            // Calcular la escala para ajustar la imagen al tamaño de la página
            float scale = Math.min(pageWidth / imageWidth, pageHeight / imageHeight);

            // Calcular las nuevas dimensiones de la imagen
            float scaledWidth = imageWidth * scale;
            float scaledHeight = imageHeight * scale;
            if (scaledHeight == pageHeight) {
              scaledWidth *= 0.7F;
              scaledHeight *= 0.7F;
            }
            // Calcular la posición de la imagen centrada en la página
            float x = (pageWidth - scaledWidth) / 2;
            float y = (pageHeight - scaledHeight) / 2;

            // Dibujar la imagen escalada y centrada en la página
            contentStream.drawImage(image, x, y, scaledWidth, scaledHeight);
          }
        }
      }

      // Guardar el documento
      String fileName = canyon.getName() + ".pdf";
      document.save(fileName);

      // fixme download document
      if (Boolean.TRUE.equals(email)) {
        sendEmail.sendCanyonDocument(document, fileName);
      }
      File pdfFile = new File(fileName);
      if (pdfFile.exists()) {
        return new FileSystemResource(pdfFile);
      } else {
        throw new RuntimeException("Error: el archivo PDF no se encontró.");
      }

    } catch (IOException | MessagingException e) {
      throw new RuntimeException(e);
    }
  }

  private static void addText(PDPageContentStream contentStream, String text, String textType)
      throws IOException {

    switch (textType) {
      case "title":
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText(text);
        contentStream.newLineAtOffset(0, -15);
        break;

      case "secondTitle":
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText(text);
        break;
      case "text":
      default:
        List<String> lines = splitTextIntoLines(text, PDType1Font.HELVETICA, 7);
        lines.forEach(
            line -> {
              try {
                contentStream.setFont(PDType1Font.HELVETICA, 9);
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText(line.trim());
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            });
        break;
    }
  }

  private static List<String> splitTextIntoLines(String text, PDType1Font font, float sizeFont)
      throws IOException {
    List<String> lines = new ArrayList<>();
    String[] words = text.split(" ");
    StringBuilder line = new StringBuilder();

    for (String word : words) {
      String testLine = line + word + " ";
      float textWidth = font.getStringWidth(testLine) / 1000 * sizeFont;

      if (textWidth > PAGE_WIDTH - 2 * MARGIN) {
        lines.add(line.toString().trim());
        line = new StringBuilder(word + " ");
      } else {
        line.append(word).append(" ");
      }
    }

    if (!line.isEmpty()) {
      lines.add(line.toString().trim());
    }

    return lines;
  }

  private static int compareSteps(String step1, String step2) {
    // Extraer la parte numérica y la parte de sufijo
    int number1 = extractNumber(step1);
    int number2 = extractNumber(step2);
    String suffix1 = extractSuffix(step1);
    String suffix2 = extractSuffix(step2);

    // Comparar primero por la parte numérica
    int numberComparison = Integer.compare(number1, number2);
    if (numberComparison != 0) {
      return numberComparison;
    }

    // Si los números son iguales, comparar por el sufijo
    return suffix1.compareTo(suffix2);
  }

  // Extraer la parte numérica del string
  private static int extractNumber(String step) {
    String numberPart = step.replaceAll("\\D", ""); // Eliminar caracteres no numéricos
    return numberPart.isEmpty() ? 0 : Integer.parseInt(numberPart);
  }

  // Extraer el sufijo del string
  private static String extractSuffix(String step) {
    return step.replaceAll("\\d", ""); // Eliminar caracteres numéricos
  }
}
