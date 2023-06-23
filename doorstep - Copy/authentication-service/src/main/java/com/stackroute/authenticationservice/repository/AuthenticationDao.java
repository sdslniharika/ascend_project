package com.stackroute.authenticationservice.repository;

import com.stackroute.authenticationservice.entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationDao extends JpaRepository<LoginDetails, String> {


}
