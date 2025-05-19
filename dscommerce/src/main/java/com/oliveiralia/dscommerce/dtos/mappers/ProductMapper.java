package com.oliveiralia.dscommerce.dtos.mappers;

import com.oliveiralia.dscommerce.dtos.ProductDto;
import com.oliveiralia.dscommerce.entities.Product;

public class ProductMapper {
	
	public static Product fromDto(ProductDto dto) {
		if(dto == null) return null;
		return new Product(null, dto.getName(), dto.getDescription(), dto.getPrice(), dto.getImgURL());
		
	}
	
	public static ProductDto fromEntity(Product product) {
		if(product == null) return null;
		return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl());
	}
	
	public static void copyDtoToEntity(ProductDto dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgURL());
	}
}
