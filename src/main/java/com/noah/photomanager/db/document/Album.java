package com.noah.photomanager.db.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "albumbs")
@Data
@Builder
public class Album {

	@Id
	private String id;
	private String title;
	@DBRef
	private String owner;
	private List<Photo> photos;
}
