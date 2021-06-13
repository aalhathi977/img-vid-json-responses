package com.testdifferentresponses.DiffResponses;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@org.springframework.stereotype.Controller
@RequestMapping(value = "/Controller")
public class Controller {

    private static String UPLOADED_FOLDER = "/home/aalhathi/Downloads/uploaded-files/";


    @GetMapping("/controller-index")
    public String index() {
        return "controller-index";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("image") MultipartFile file) {
        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }
}
