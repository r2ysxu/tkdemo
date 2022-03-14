package com.tk.demo.graphql.query.model.item;

import java.util.List;

import graphql.relay.PageInfo;

public class ItemEdgeConnection {

	private List<ItemEdge> edges;
	private PageInfo pageInfo;
	private Long total;

	public List<ItemEdge> getEdges() {
		return edges;
	}

	public ItemEdgeConnection setEdges(List<ItemEdge> edges) {
		this.edges = edges;
		return this;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public ItemEdgeConnection setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
		return this;
	}

	public Long getTotal() {
		return total;
	}

	public ItemEdgeConnection setTotal(Long total) {
		this.total = total;
		return this;
	}
}
