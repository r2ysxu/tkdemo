package com.tk.demo.graphql.mutation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.tk.demo.graphql.query.ItemBuilder;
import com.tk.demo.graphql.query.model.Node;
import com.tk.demo.graphql.query.model.item.Item;
import com.tk.demo.graphql.query.model.item.ItemEdge;
import com.tk.demo.jpa.model.ItemEntity;
import com.tk.demo.service.ItemService;

@Component
public class ItemMutation implements GraphQLMutationResolver {

	@Autowired
	private ItemService itemService;

	public ItemEdge createItem(String message) {
		ItemEntity itemEntity = itemService.addItem(message);
		ItemEdge itemEdge = new ItemEdge(ItemBuilder.buildFromEntity(itemEntity));
		return itemEdge;
	}

	public List<Item> createItems(List<String> messages) {
		Iterable<ItemEntity> items = itemService.addItems(messages);
		return ItemBuilder.buildFromList(items);
	}

	public Boolean deleteItem(String id) {
		return itemService.deleteItem(Node.decodeId(id));
	}
}
