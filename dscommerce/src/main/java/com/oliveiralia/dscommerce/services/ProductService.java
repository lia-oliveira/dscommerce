package com.oliveiralia.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oliveiralia.dscommerce.entities.Product;
import com.oliveiralia.dscommerce.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional
	public Product findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
	}

}
