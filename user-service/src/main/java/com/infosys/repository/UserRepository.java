package com.infosys.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.infosys.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {

}
