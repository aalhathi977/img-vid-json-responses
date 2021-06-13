package com.testdifferentresponses.DiffResponses;

import com.sun.net.httpserver.Headers;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerRequest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping(value = "/RestContoller")
public class Rest_Controller {

//    private static String UPLOADED_FOLDER = "/home/aalhathi/Downloads/uploaded-files/video.mp4";
    private static String UPLOADED_FOLDER = "/home/aalhathi/Downloads/uploaded-files/auth-box-ar.png";

    @GetMapping(value = "json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity returnJson(){
        Map<String, String> students = new HashMap<>();
        students.put("0", "Aziz");
        students.put("1", "omar");
        students.put("2", "zaid");
        students.put("3", "majeed");
        return new ResponseEntity(students, HttpStatus.OK);
    }

    @GetMapping(value = "/downlaod-vid", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity DownloadVideoToDisk() throws IOException {
        File file = new File(UPLOADED_FOLDER.toString());
        InputStream in = new FileInputStream(file);
        byte[] bytes = in.readAllBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Content-Type", "video/mp4");
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).body(bytes);
    }

    @GetMapping(value = "/get-vid", produces = "video/mp4")
    public ResponseEntity PlayVideoInBrowser() throws IOException {
        File file = new File(UPLOADED_FOLDER.toString());
        InputStream in = new FileInputStream(file);
        byte[] bytes = in.readAllBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Content-Type", ".mp4");
        return new ResponseEntity<>(in, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/get-image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity getImage() throws IOException {
        File file = new File(UPLOADED_FOLDER.toString());
        InputStream in = new FileInputStream(file);
        byte[] bytes = in.readAllBytes();
        return ResponseEntity.ok(bytes);
    }

    @GetMapping(value = "/download-image", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity downloadImage() throws IOException {
        File file = new File(UPLOADED_FOLDER.toString());
        InputStream in = new FileInputStream(file);
        byte[] bytes = in.readAllBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Content-Type", "image/png");
//        return ResponseEntity.ok(bytes);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

}
