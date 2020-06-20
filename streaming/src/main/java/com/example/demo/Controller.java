package com.example.demo;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class Controller {

    @RequestMapping(value = "/image", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)

    public void getImage(HttpServletResponse response) throws IOException {

        ClassPathResource imgFile = new ClassPathResource("image/amazing.jpg");

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }

    @RequestMapping(value = "/image2", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {

        ClassPathResource imgFile = new ClassPathResource("image/image2.jpg");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    @RequestMapping(value = "/image3", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImag() throws IOException {

        ClassPathResource imgFile = new ClassPathResource("image/image3.jpg");

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST,
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            File testFile = new File("test.jpg");
            FileUtils.writeByteArrayToFile(testFile, file.getBytes());
//            List<String> lines = FileUtils.readLines(testFile);
//            lines.forEach(line -> System.out.println(line));
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Done", HttpStatus.OK);
    }
}