package com.oliveiralia.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oliveiralia.dscommerce.dtos.ProductDto;
import com.oliveiralia.dscommerce.dtos.mappers.ProductMapper;
import com.oliveiralia.dscommerce.entities.Product;
import com.oliveiralia.dscommerce.repositories.ProductRepository;
import com.oliveiralia.dscommerce.services.exceptions.DatabaseException;
import com.oliveiralia.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		Product product = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado."));
		return ProductMapper.fromEntity(product);		
	}
	
	@Transactional
	public ProductDto insert(ProductDto dto) {
		Product entity = ProductMapper.fromDto(dto);		
		entity = repository.save(entity);
		return ProductMapper.fromEntity(entity);		
	}
	
	@Transactional
	public ProductDto update(Long id, ProductDto dto) {
		try {
			Product entity = repository.getReferenceById(id);
			ProductMapper.copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return ProductMapper.fromEntity(entity);
		}
		catch (EntityNotFoundException e) {
			 throw new ResourceNotFoundException("Recurso não encontrado.");
		}
 	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado.");
		}
		try {
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
		
	}
}
