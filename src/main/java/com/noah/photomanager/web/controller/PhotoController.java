package com.noah.photomanager.web.controller;

import com.noah.photomanager.db.document.Photo;
import com.noah.photomanager.db.repository.PhotoRepository;
import com.noah.photomanager.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/photos")
@AllArgsConstructor
public class PhotoController {

	private final PhotoService photoService;

	@PostMapping("/add")
	public ResponseEntity<String> addPhoto(@RequestParam("title") String title,
						 @RequestParam("image") MultipartFile image) throws IOException {
		return ResponseEntity.ok(photoService.addPhoto(title, image));
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getPhoto(@PathVariable String id) {
		Photo photo = photoService.getPhoto(id);
		return ResponseEntity.ok(
				Base64.getEncoder().encodeToString(photo.getImage().getData())
		);
	}
}
