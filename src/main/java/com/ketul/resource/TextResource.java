package com.ketul.resource;


import com.ketul.service.TextService;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.notFound;

@RestController

public class TextResource {
    @Autowired
    private TextService textService;

    @PostMapping("/")
    public ResponseEntity<?> getTextIntoImage(@RequestParam("file") MultipartFile multipartFile) {
        return textService.getTextIntoImage(multipartFile);
    }
}
