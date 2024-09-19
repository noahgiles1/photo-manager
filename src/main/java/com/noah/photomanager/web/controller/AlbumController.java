package com.noah.photomanager.web.controller;

import com.noah.photomanager.db.document.Album;
import com.noah.photomanager.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/album")
@AllArgsConstructor
public class AlbumController {

	private final PhotoService photoService;

	@PostMapping("/add")
	public ResponseEntity<Album> addAlbum(@RequestParam("title") String title,
										  @RequestParam("owner") String owner,
										  @RequestParam("title") String photos) {
		return null;
	}
}
