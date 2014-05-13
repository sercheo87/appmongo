package com.data.core;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	public User findByName(String Name);

	public List<User> findByLastName(String lastName);
}
