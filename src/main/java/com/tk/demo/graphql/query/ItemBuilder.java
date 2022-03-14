package com.tk.demo.graphql.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.tk.demo.graphql.query.model.Node;
import com.tk.demo.graphql.query.model.item.Item;
import com.tk.demo.graphql.query.model.item.ItemEdge;
import com.tk.demo.graphql.query.model.item.ItemEdgeConnection;
import com.tk.demo.jpa.model.ItemEntity;

import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.DefaultPageInfo;
import graphql.relay.PageInfo;

public class ItemBuilder implements QueryBuilder<Item, ItemEntity> {

	public static ItemEdgeConnection buildFromPage(Page<ItemEntity> items, Long total) {
		ItemEdgeConnection itemEdgeConnection = new ItemEdgeConnection();
		itemEdgeConnection.setPageInfo(createPageInfo(items)).setEdges(createPageItems(items));
		itemEdgeConnection.setTotal(total);
		return itemEdgeConnection;
	}

	public static List<Item> buildFromList(Iterable<ItemEntity> itemEntities) {
		List<Item> itemsList = new ArrayList<>();
		Iterator<ItemEntity> itemIterator = itemEntities.iterator();
		while (itemIterator.hasNext()) {
			itemsList.add(ItemBuilder.buildFromEntity(itemIterator.next()));
		}
		return itemsList;
	}

	public static Item buildFromEntity(ItemEntity itemEntity) {
		return new Item(itemEntity.getId(), itemEntity.getMessage());
	}

	private static PageInfo createPageInfo(Page<ItemEntity> items) {
		if (items.isEmpty()) return new DefaultPageInfo(null, null, items.hasPrevious(), items.hasNext());
		List<ItemEntity> itemContent = items.getContent();
		Long firstId = itemContent.get(0).getId();
		Long lastId = itemContent.get(items.getNumberOfElements() - 1).getId();
		ConnectionCursor startCursor = new DefaultConnectionCursor(Node.generateId(firstId));
		ConnectionCursor endCursor = new DefaultConnectionCursor(Node.generateId(lastId));
		return new DefaultPageInfo(startCursor, endCursor, items.hasPrevious(), items.hasNext());
	}

	private static List<ItemEdge> createPageItems(Page<ItemEntity> items) {
		return items.stream().map((item) -> {
			return new ItemEdge(new Item(item.getId(), item.getMessage()));
		}).collect(Collectors.toList());
	}
}
