package com.tk.demo.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class ItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String message;
	@CreationTimestamp
	private Date createdAt;

	public Long getId() {
		return id;
	}

	public ItemEntity setId(Long id) {
		this.id = id;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ItemEntity setMessage(String message) {
		this.message = message;
		return this;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
