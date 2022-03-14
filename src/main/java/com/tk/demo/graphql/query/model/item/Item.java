package com.tk.demo.graphql.query.model.item;

import com.tk.demo.graphql.query.model.Node;

public class Item implements Node {

	private final Long id;
	private String message;

	public Item(Long id, String message) {
		this.id = id;
		this.message = message;
	}

	@Override
	public String getId() {
		return Node.generateId(id);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
