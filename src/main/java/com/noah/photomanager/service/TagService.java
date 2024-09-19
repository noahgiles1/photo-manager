package com.noah.photomanager.service;

import com.noah.db.document.User;
import com.noah.photomanager.db.document.Tag;
import com.noah.photomanager.db.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TagService {

	private final TagRepository tagRepository;

	public Tag addTag(User owner, String tagText) {
		Tag tag = Tag.builder()
				.owner(owner)
				.tag(tagText)
				.build();
		return tagRepository.insert(tag);
	}
}
