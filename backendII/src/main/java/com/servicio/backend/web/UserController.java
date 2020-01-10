package com.servicio.backend.web;

import com.servicio.backend.entity.User;
import com.servicio.backend.exception.RecordNotFoundException;
import com.servicio.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id).get());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public ResponseEntity<List<User>> getAllByExample(@RequestBody User user){
        return ResponseEntity.ok(userService.findUserByExample(user));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> add(@Valid @RequestBody User newUser){
        return ResponseEntity.ok(userService.addUser(newUser));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<User> updateById(@PathVariable Long id, @RequestBody User user){
        User resp = userService.updateUserById(id, user);
        return ResponseEntity.ok(resp);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if (!userService.deleteUserById(id))
            throw new RecordNotFoundException("Could not find user: " + id);
        return new ResponseEntity(HttpStatus.MOVED_PERMANENTLY);
    }
}
