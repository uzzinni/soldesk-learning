package com.poseidon.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GuestBook extends BaseEntity{
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gno;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(length = 1500, nullable = false)
	private String content;
	
	@Column(length = 50, nullable = false)
	private String wirter;
	
	public void changeTitle(String title) {
		this.title = title;
	}
	
	public void changeContent(String content) {
		this.content = content;
	}
	
}
