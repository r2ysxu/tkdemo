package com.tk.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tk.demo.jpa.model.ItemEntity;
import com.tk.demo.jpa.repo.ItemRepo;

@Service
public class ItemService {
	@Autowired
	ItemRepo itemRepo;

	public Page<ItemEntity> fetchItemPage(Long cursor, Integer size) {
		return itemRepo.findByIdGreaterThan(cursor, PageRequest.of(0, size, Sort.by("id").ascending()));
	}
	
	public Long fetchTotalItems() {
		return itemRepo.count();
	}

	public Boolean deleteItem(Long id) {
		boolean itemExist = itemRepo.existsById(id);
		if (itemExist) itemRepo.deleteById(id);
		return itemExist;
	}

	public ItemEntity addItem(String message) {
		return itemRepo.save(new ItemEntity().setMessage(message));
	}

	public Iterable<ItemEntity> addItems(List<String> messages) {
		List<ItemEntity> itemEntities = messages.stream().map(message -> {
			return new ItemEntity().setMessage(message);
		}).toList();
		return itemRepo.saveAll(itemEntities);
	}

	public void purgeItems() {
		itemRepo.deleteAll();
	}
}
