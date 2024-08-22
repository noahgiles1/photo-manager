package com.noah.photomanager.db.repository;

import com.noah.photomanager.db.document.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {
}
