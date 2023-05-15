package com.demoJPA.springjpa.repository;

import com.demoJPA.springjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
//    @Query(value = "SELECT c.email FROM User c WHERE c.email = :email", nativeQuery = true)
//    User findByUserName(@Param("email") String email);
}

