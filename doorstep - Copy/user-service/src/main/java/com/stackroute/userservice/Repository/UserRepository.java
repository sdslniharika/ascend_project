package com.stackroute.userservice.Repository;

import com.stackroute.userservice.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findByEmailId(String emailId);
}
