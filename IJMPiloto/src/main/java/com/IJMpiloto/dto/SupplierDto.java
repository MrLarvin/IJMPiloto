package com.IJMpiloto.dto;

import java.util.Set;

public class SupplierDto {

	private String code;
	
	private String name;

	private Set<ProductDto> productsDto;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProductDto> getProductsDto() {
		return productsDto;
	}

	public void setProductsDto(Set<ProductDto> productsDto) {
		this.productsDto = productsDto;
	}
	
	
}
