package com.hcl.booklendingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.booklendingsystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
