package com.ketul.resource;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
public class TextResource {

    @GetMapping("/")
    public ResponseEntity<Object> getTextIntoImage() {
        File file = new File("hello.png");
        System.out.println(file.exists());
        ITesseract instance = new Tesseract();
        instance.setDatapath("tessdata");

        try {
            String result = instance.doOCR(file);
            System.out.println(result);
            return ResponseEntity.ok(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return notFound().build();
        }
    }
}
