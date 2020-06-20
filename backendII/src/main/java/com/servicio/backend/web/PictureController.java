package com.servicio.backend.web;

import com.servicio.backend.entity.Picture;
import com.servicio.backend.exception.RecordNotFoundException;
import com.servicio.backend.service.PictureService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/image")
public class PictureController {

    @RequestMapping(value = "/image", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(HttpServletResponse response) throws IOException {

        ClassPathResource imgFile = new ClassPathResource("pictures/amazing.jpg");

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }

    @RequestMapping(value = "/image2", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {

        ClassPathResource imgFile = new ClassPathResource("pictures/image2.jpg");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    @RequestMapping(value = "/image3", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImag() throws IOException {

        ClassPathResource imgFile = new ClassPathResource("pictures/image3.jpg");

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST,
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @CrossOrigin
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            File testFile = new File("src/main/resources/pictures"+file.getOriginalFilename());
            FileUtils.writeByteArrayToFile(testFile, file.getBytes());
//            List<String> lines = FileUtils.readLines(testFile);
//            lines.forEach(line -> System.out.println(line));
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Done", HttpStatus.OK);
    }

    @Autowired
    private PictureService pictureService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Picture>> getAll(){
        return ResponseEntity.ok(pictureService.getAllPictures());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Picture> getById(@PathVariable Long id){
        return ResponseEntity.ok(pictureService.getPictureById(id).get());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public ResponseEntity<List<Picture>> getAllByExample(@RequestBody Picture picture){
        return ResponseEntity.ok(pictureService.findPictureByExample(picture));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Picture> add(@Valid @RequestBody Picture newPicture){
        return ResponseEntity.ok(pictureService.addPicture(newPicture));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Picture> updateById(@PathVariable Long id, @RequestBody Picture picture){
        Picture resp = pictureService.updatePictureById(id, picture);
        return ResponseEntity.ok(resp);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if (!pictureService.deletePictureById(id))
            throw new RecordNotFoundException("Could not find picture: " + id);
        return new ResponseEntity(HttpStatus.MOVED_PERMANENTLY);
    }
}