package com.noah.photomanager.service;

import com.noah.photomanager.db.document.Album;
import com.noah.photomanager.db.document.Photo;
import com.noah.photomanager.db.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlbumService {

	private final AlbumRepository albumRepository;

	public String addAlbum(String title, String owner, List<Photo> photos) {
		Album album = Album.builder()
    			.title(title)
    			.owner(owner)
    			.photos(photos)
    			.build();
    	albumRepository.save(album);
		return "he";
	}

}
