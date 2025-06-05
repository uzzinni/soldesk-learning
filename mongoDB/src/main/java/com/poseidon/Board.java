package com.poseidon;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collation = "board")
public class Board {
	@Id
	private String id;
	private String title;
	private String content;
	private LocalDateTime date;
	private String name;
}
