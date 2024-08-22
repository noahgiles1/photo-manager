package com.noah.photomanager.db.document;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
@Data
public class Photo {

	@Id
	private String id;
	@NonNull
	private String title;
	private Binary image;
}
