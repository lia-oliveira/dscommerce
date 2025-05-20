package com.oliveiralia.dscommerce.dtos;

import com.oliveiralia.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDto {
	
	private Long id;
	
	@Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres.")
	@NotBlank(message="Campo requerido.")
	private String name;
	
	@Size(min = 10, message = "A descrição precisa ter no mínimo 10 caracteres.")
	@NotBlank(message="Campo requerido.")
	private String description;
	
	@Positive(message = "O preço deve ser positivo.")
	private Double price;
	
	private String imgURL;
	
	public ProductDto() {}

	public ProductDto(Long id, String name, String description, Double price, String imgURL) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgURL = imgURL;
	}
	
	public ProductDto(Product entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgURL = entity.getImgUrl();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgURL() {
		return imgURL;
	}
}
