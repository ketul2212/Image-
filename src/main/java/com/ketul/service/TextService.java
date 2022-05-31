package com.ketul.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

@Service
public class TextService {
    public ResponseEntity<?> getTextIntoImage(MultipartFile multipartFile) {

        if(multipartFile.isEmpty())
            return ResponseEntity.ok("File is empty please select a file");

        File file = new File("uploads/output.png");

        ITesseract iTesseract = new Tesseract();
        iTesseract.setDatapath("tessdata");

        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());

            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            if(Objects.requireNonNull(extension).equalsIgnoreCase("jpg")) {
                BufferedImage image = ImageIO.read(file);
                ImageIO.write(image, "png", file);
            }

        } catch (IOException e) {
            return ResponseEntity.ok("some error occur to write MultipartFile to File or convert Image to png");
        }

        String result;
        try {
            result = iTesseract.doOCR(file);
        } catch (TesseractException e) {
            return ResponseEntity.ok("some error occur to convert images to Text");
        }

        System.out.println(result);
        return ResponseEntity.ok(result);
    }
}
