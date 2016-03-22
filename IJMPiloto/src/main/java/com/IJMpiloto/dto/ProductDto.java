package com.IJMpiloto.dto;

public class ProductDto {
	
	private String code;
	
	private String description;
	
	private SupplierDto supplierDto;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SupplierDto getSupplierDto() {
		return supplierDto;
	}

	public void setSupplierDto(SupplierDto supplierDto) {
		this.supplierDto = supplierDto;
	}
	
}
