package com.hmsapp.Repository;

import com.hmsapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmailId(String emailId);  // Corrected method name
     Optional<User> findByUserName(String userName);
    Optional<User> findByMobileNumber(String mobileNumber);
}
