package com.servicio.backend.service;

import com.servicio.backend.entity.Picture;
import com.servicio.backend.exception.RecordNotFoundException;
import com.servicio.backend.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRpo;

    public List<Picture> getAllPictures(){
        return pictureRpo.findAll();
    }

    public Optional<Picture> getPictureById(long id){
        return pictureRpo.findById(id);
    }

    public Picture addPicture(Picture newPicture){
        newPicture.setCreate(new Date());
        return pictureRpo.save(newPicture);
    }

    public Picture updatePictureById(Long id_Picture, Picture picture){
        Picture resp = pictureRpo.findById(id_Picture).get();
        resp.setId(id_Picture);
        if (picture.getName() != null)
            resp.setName(picture.getName());
        return pictureRpo.save(resp);
    }

    public boolean deletePictureById(long id) throws RecordNotFoundException {
        if (!pictureRpo.existsById(id))
            throw new RecordNotFoundException("Could not find picture: " + id);
        pictureRpo.deleteById(id);
        return !pictureRpo.existsById(id);
    }

    public List<Picture> findPictureByExample(Picture picture){
        return pictureRpo.findAll(Example.of(picture));
    }

    public boolean existPictureById(long id){
        return pictureRpo.existsById(id);
    }

    public boolean existPictureByExample(Picture examplePicture){
        return pictureRpo.exists(Example.of(examplePicture));
    }
}
