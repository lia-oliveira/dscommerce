package com.oliveiralia.dscommerce.services;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<ProductDto> findAll(Pageable pageable){
		Page<Product> result = repository.findAll(pageable);
		return result.map(ProductMapper::fromEntity);
	}
	
	@Transactional(readOnly = true)
	public ProductDto findById(Long id) {
		Optional<Product> result = repository.findById(id);
		Product product = result.get();
		ProductDto dto = ProductMapper.fromEntity(product);
		return dto;
	}

}
