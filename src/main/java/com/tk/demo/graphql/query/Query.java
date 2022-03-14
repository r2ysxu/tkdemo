package com.tk.demo.graphql.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tk.demo.graphql.query.model.Node;
import com.tk.demo.graphql.query.model.item.ItemEdgeConnection;
import com.tk.demo.service.ItemService;

@Component
public class Query implements GraphQLQueryResolver {

	@Autowired
	private ItemService itemService;

	public Query() {
	}

	public ItemEdgeConnection getItems(Integer first, String after) {
		Long cursorId = after == null ? 0L : Node.decodeId(after);
		return ItemBuilder.buildFromPage(itemService.fetchItemPage(cursorId, first), itemService.fetchTotalItems());
	}
}
