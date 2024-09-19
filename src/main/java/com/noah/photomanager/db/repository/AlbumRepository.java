package com.noah.photomanager.db.repository;

import com.noah.photomanager.db.document.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
}
