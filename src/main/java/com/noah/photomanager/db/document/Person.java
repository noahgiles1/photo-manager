package com.noah.photomanager.db.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "people")
@Data
@Builder
public class Person {

	@Id
	private String id;
	@DBRef
	private String owner;
	private String name;
}
