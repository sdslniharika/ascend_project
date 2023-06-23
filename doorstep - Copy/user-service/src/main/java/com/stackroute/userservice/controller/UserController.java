package com.stackroute.userservice.controller;

import com.stackroute.userservice.exceptions.IdNotFound;
import com.stackroute.userservice.exceptions.ProfileAlreadyExist;
import com.stackroute.userservice.exceptions.ResourceNotFound;
import com.stackroute.userservice.exceptions.UnauthorisedException;
import com.stackroute.userservice.model.UpdateUserEntity;
import com.stackroute.userservice.model.UserEntity;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public UserEntity createUserProfile(@RequestBody UserEntity userEntity) throws ProfileAlreadyExist {
        return userService.createProfile(userEntity);
    }

    @GetMapping("/AllUsers")
    public ResponseEntity<?> getAllUserDetails() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findByEmailId/{emailId}")
    public ResponseEntity<UserEntity> getUserByEmailId(@PathVariable String emailId) throws ResourceNotFound {
        UserEntity userEntity = userService.getUserByEmailId(emailId);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);

    }

    @DeleteMapping("/deletebyEmail/{emailId}")
    public ResponseEntity<?> deleteUser(@RequestHeader HttpHeaders headers, @PathVariable("emailId") String emailId) throws IdNotFound, UnauthorisedException {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        return new ResponseEntity<>(userService.deleteUserDetails(emailId, token.replace("Bearer ", "")), HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateUser/{emailId}")
    public ResponseEntity<UserEntity> updateUser(@RequestHeader HttpHeaders headers, @PathVariable String emailId, @RequestBody UpdateUserEntity userEntity) throws ResourceNotFound, UnauthorisedException {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        return ResponseEntity.ok().body(this.userService.UpdateProfile(userEntity, emailId, token.replace("Bearer ", "")));
    }
}