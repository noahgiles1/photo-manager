package com.noah.photomanager.web.controller;

import com.noah.db.document.User;
import com.noah.photomanager.db.document.Album;
import com.noah.photomanager.db.document.Person;
import com.noah.photomanager.db.document.Photo;
import com.noah.photomanager.db.document.Tag;
import com.noah.photomanager.service.PhotoService;
import com.noah.photomanager.web.WebClientConfig;
import jakarta.servlet.http.HttpServletRequest;
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
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
@AllArgsConstructor
public class PhotoController {

	private final PhotoService photoService;
	private final WebClientConfig webClient;

	@PostMapping("/add")
	public ResponseEntity<String> addPhoto(@RequestParam("title") String title,
										   @RequestParam("image") MultipartFile image,
										   @RequestParam("userId") String owner,
										   @RequestParam(value = "albums", required = false) List<Album> albums,
										   @RequestParam(value = "tags", required = false) List<Tag> tags,
										   @RequestParam(value = "people", required = false) List<Person> people,
										   @RequestParam(value = "date", required = false) Date date,
										   HttpServletRequest httpServletRequest) throws IOException {
		User user = webClient.getUser(httpServletRequest, owner);
		return ResponseEntity.ok(photoService.addPhoto(title, image, user, albums, tags, people, date));
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getPhoto(@PathVariable String id) {
		Photo photo = photoService.getPhoto(id);
		return ResponseEntity.ok(
				Base64.getEncoder().encodeToString(photo.getImage().getData())
		);
	}
}
