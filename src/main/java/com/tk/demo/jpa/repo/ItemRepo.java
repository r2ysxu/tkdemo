package com.tk.demo.jpa.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.tk.demo.jpa.model.ItemEntity;

public interface ItemRepo extends PagingAndSortingRepository<ItemEntity, Long> {
	public Page<ItemEntity> findByIdGreaterThan(Long id, Pageable pageable);
}
