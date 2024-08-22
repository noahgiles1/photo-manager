package com.noah.photomanager.service;

import com.noah.photomanager.db.document.Photo;
import com.noah.photomanager.db.repository.PhotoRepository;
import com.noah.photomanager.exceptions.PhotoNotFoundException;
import lombok.AllArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.MessageFormat;

@Service
@AllArgsConstructor
public class PhotoService {

	private final PhotoRepository photoRepo;

	public String addPhoto(String title, MultipartFile file) throws IOException {
		Photo photo = new Photo(title);
		photo.setImage(
				new Binary(BsonBinarySubType.BINARY, file.getBytes()));
		photo = photoRepo.insert(photo);
		return photo.getId();
	}

	public Photo getPhoto(String id) {
		return photoRepo.findById(id)
				.orElseThrow(() -> new PhotoNotFoundException(
						MessageFormat.format("Photo {0} not found", id)
				));
	}
}
