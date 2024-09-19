package com.noah.photomanager.db.repository;

import com.noah.photomanager.db.document.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
}
