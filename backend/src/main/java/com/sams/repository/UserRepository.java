package com.sams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sams.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
