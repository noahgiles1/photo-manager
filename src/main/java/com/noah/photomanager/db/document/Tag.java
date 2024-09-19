package com.noah.photomanager.db.document;

import com.noah.db.document.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tags")
@Data
@Builder
public class Tag {

	@Id
	private String id;
	@DBRef
	private User owner;
	private String tag;
}
