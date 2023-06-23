package com.stackroute.userservice.service;

import com.stackroute.userservice.exceptions.IdNotFound;
import com.stackroute.userservice.exceptions.ProfileAlreadyExist;
import com.stackroute.userservice.exceptions.ResourceNotFound;
import com.stackroute.userservice.exceptions.UnauthorisedException;
import com.stackroute.userservice.model.UpdateUserEntity;
import com.stackroute.userservice.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserEntity createProfile(UserEntity userEntity) throws ProfileAlreadyExist;

    UserEntity getUserByEmailId(String emailId) throws ResourceNotFound;
    /* UserEntity getUserByRole(Role role);*/

    UserEntity UpdateProfile(UpdateUserEntity userEntity, String emailId, String token) throws ResourceNotFound, UnauthorisedException;

    List<UserEntity> findAll();

    UserEntity deleteUserDetails(String emailId, String token) throws IdNotFound, UnauthorisedException;


}

