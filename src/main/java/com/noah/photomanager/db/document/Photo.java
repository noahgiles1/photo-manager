package com.noah.photomanager.db.document;

import com.noah.db.document.User;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "photos")
@Data
@Builder
public class Photo {

	@Id
	private String id;
	@NonNull
	private String title;
	@NonNull
	@DBRef
	private User owner;
	private Binary image;
	@DBRef
	private List<Album> albums;
	@DBRef
	private List<Tag> tags;
	@DBRef
	private List<Person> people;
	private Date date;
}
