package com.noah.photomanager.db.repository;

import com.noah.photomanager.db.document.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepository extends MongoRepository<Tag, String> {
}
