package com.jersey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jersey.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
