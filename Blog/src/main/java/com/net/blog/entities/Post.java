package com.net.blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Post 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String text;
	
	@ManyToOne
	private User user;

	public Post(String text, User user)
	{
		this.text = text;
		this.user = user;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
}
