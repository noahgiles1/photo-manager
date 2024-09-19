package com.noah.photomanager.web.controller;

import com.noah.db.document.User;
import com.noah.photomanager.db.document.Tag;
import com.noah.photomanager.exceptions.UnauthorizedException;
import com.noah.photomanager.service.TagService;
import com.noah.photomanager.web.WebClientConfig;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tag")
@AllArgsConstructor
public class TagController {

	private final TagService tagService;
	private final WebClientConfig webClientConfig;

	@PostMapping("/add")
	public ResponseEntity<Object> addTag(@RequestParam("owner") String owner,
									  @RequestParam("tag") String tag,
									  HttpServletRequest httpServletRequest) {
		try {
			User user = webClientConfig.getUser(httpServletRequest, owner);
			return ResponseEntity.ok(tagService.addTag(user, tag));
		} catch (UnauthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
		}
	}
}
