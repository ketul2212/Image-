package com.ketul.resource;


import com.ketul.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController

public class TextResource {
    @Autowired
    private TextService textService;

    @PostMapping("/")
    public ResponseEntity<?> getTextIntoImage(@RequestParam("file") MultipartFile multipartFile) {
        return textService.getTextIntoImage(multipartFile);
    }
}
