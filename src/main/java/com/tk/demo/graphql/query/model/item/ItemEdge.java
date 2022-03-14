package com.tk.demo.graphql.query.model.item;

import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;

public class ItemEdge implements Edge<Item> {

	private ConnectionCursor cursor;
	private Item node;

	public ItemEdge(Item node) {
		this.cursor = new DefaultConnectionCursor(node.getId());
		this.node = node;
	}

	@Override
	public Item getNode() {
		return node;
	}

	@Override
	public ConnectionCursor getCursor() {
		return cursor;
	}
}
