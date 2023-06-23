package com.stackroute.userservice.service;

import com.stackroute.userservice.Repository.UserRepository;
import com.stackroute.userservice.exceptions.IdNotFound;
import com.stackroute.userservice.exceptions.ProfileAlreadyExist;
import com.stackroute.userservice.exceptions.ResourceNotFound;
import com.stackroute.userservice.exceptions.UnauthorisedException;
import com.stackroute.userservice.model.UpdateUserEntity;
import com.stackroute.userservice.model.UserEntity;
import com.stackroute.userservice.model.UserModel;
import com.stackroute.userservice.rabbitmq.UserServicePublisher;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserServicePublisher userServicePublisher;
    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity createProfile(UserEntity userEntity) throws ProfileAlreadyExist {
        if (this.userRepository.findById(userEntity.getEmailId()).isPresent()) {
            throw new ProfileAlreadyExist();
        }
        UserModel userModel = new UserModel();
        userModel.setEmailId(userEntity.getEmailId());
        userModel.setCustomerId(userEntity.getCustomerId());
        userModel.setPassword(userEntity.getPassword());
        userModel.setRole(userEntity.getRole().toString());
        userServicePublisher.sendMessagetoRabbitMq(userModel);

        userEntity.setPassword(null);

        UserEntity user = userRepository.save(userEntity);
        return user;
    }

    @Override
    public UserEntity getUserByEmailId(String emailId) throws ResourceNotFound {
        UserEntity byOrderId = userRepository.findByEmailId(emailId);
        if (byOrderId != null) {
            return userRepository.findByEmailId(emailId);
        } else {
            throw new ResourceNotFound();
        }
    }

    @Override
    public UserEntity deleteUserDetails(String emailId, String token) throws IdNotFound, UnauthorisedException {
        UserEntity byOrderId = userRepository.findByEmailId(emailId);
        Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
        String emailIdToken = (String) claims.get("sub");
        if (!emailIdToken.equals(byOrderId.getEmailId())) {
            throw new UnauthorisedException("Unauthorised User");
        }
        if (byOrderId != null) {
            userRepository.delete(byOrderId);
            return byOrderId;
        } else {
            throw new IdNotFound();
        }

    }

    @Override
    public UserEntity UpdateProfile(UpdateUserEntity userEntity, String emailId, String token) throws ResourceNotFound, UnauthorisedException {
        UserEntity updatedUserEntity = userRepository.findByEmailId(emailId);
        Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
        String emailIdField = (String) claims.get("sub");
        if (!emailIdField.equals(updatedUserEntity.getEmailId())) {
            throw new UnauthorisedException();
        }
        if (updatedUserEntity != null) {
            updatedUserEntity.setMobileNo(userEntity.getMobileNo());
            updatedUserEntity.setFirstName(userEntity.getFirstName());
            updatedUserEntity.setMiddleName(userEntity.getMiddleName());
            updatedUserEntity.setLastName(userEntity.getLastName());
            updatedUserEntity.setCity(userEntity.getCity());
            updatedUserEntity.setDist(userEntity.getCity());
            updatedUserEntity.setDoorNo(userEntity.getDoorNo());
            updatedUserEntity.setLandmark(userEntity.getLandmark());
            updatedUserEntity.setPinCode(userEntity.getPinCode());
            updatedUserEntity.setState(userEntity.getState());
            updatedUserEntity.setStreet(userEntity.getStreet());

            return userRepository.save(updatedUserEntity);
        } else {
            throw new ResourceNotFound();
        }
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }


}

