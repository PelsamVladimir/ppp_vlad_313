package com.vlad.pp_313.controller;
import com.vlad.pp_313.model.User;
import com.vlad.pp_313.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserRestController {
    private UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getAllUsers () {
        return service.index();
    }

    @GetMapping("/users/{id}")
    public User getOneUser (@PathVariable("id") Long id) {
        return service.getUser(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user) {
        service.save(user);
        return new ResponseEntity<>(service.getUserByName(user.getName()) ,HttpStatus.OK);
    }

    @RequestMapping(value = "/users"
            , produces = MediaType.ALL_VALUE
            , method = RequestMethod.PUT)
    public ResponseEntity<User> changeUser (@RequestBody User user) {
        System.out.println("Got user = ID : " + user.getId() + " AGE : "+ user.getAge() + " NAME : " + user.getName());
        service.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> changeUser (@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<> (HttpStatus.OK);
    }
}
