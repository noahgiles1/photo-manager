package com.noah.photomanager.service;

import com.noah.db.document.User;
import com.noah.photomanager.db.document.Album;
import com.noah.photomanager.db.document.Person;
import com.noah.photomanager.db.document.Photo;
import com.noah.photomanager.db.document.Tag;
import com.noah.photomanager.db.repository.PhotoRepository;
import com.noah.photomanager.exceptions.PhotoNotFoundException;
import lombok.AllArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PhotoService {

	private final PhotoRepository photoRepo;

	public String addPhoto(String title, MultipartFile file, User owner,
						   List<Album> albums, List<Tag> tags, List<Person> people, Date date) throws IOException {
		Photo photo = Photo.builder()
				.title(title)
				.owner(owner)
				.albums(albums)
				.tags(tags)
				.people(people)
				.date(date)
				.build();
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
