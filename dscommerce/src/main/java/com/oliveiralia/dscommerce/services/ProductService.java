package com.oliveiralia.dscommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oliveiralia.dscommerce.dtos.ProductDto;
import com.oliveiralia.dscommerce.dtos.mappers.ProductMapper;
import com.oliveiralia.dscommerce.entities.Product;
import com.oliveiralia.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public List<ProductDto> findAll(){
		List<Product> result = repository.findAll();
		return result.stream()
				.map(ProductMapper::fromEntity)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ProductDto findById(Long id) {
		Optional<Product> result = repository.findById(id);
		Product product = result.get();
		ProductDto dto = ProductMapper.fromEntity(product);
		return dto;
	}

}
