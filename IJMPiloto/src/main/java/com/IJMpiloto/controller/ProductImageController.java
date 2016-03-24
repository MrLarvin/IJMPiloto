package com.IJMpiloto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IJMpiloto.dto.ProductImageDto;
import com.IJMpiloto.service.ProductImageService;

@RestController
@RequestMapping("/productImage")
public class ProductImageController {
	@Autowired
	private ProductImageService productImageService;

	// create a Product
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createProduct(@RequestBody ProductImageDto productImageDto) {
		productImageService.saveProductImage(productImageDto);
		System.out.println("A User with name " + productImageDto.getId() + " has been added");
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProduct(@PathVariable("id") long id, @RequestBody ProductImageDto productImageDto) {
		System.out.println("Updating Product " + id);
		HttpStatus status;
		try {
			productImageService.updateProductImage(productImageDto);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<Void>(status);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProductImageDto>> listAllProducts() {
		List<ProductImageDto> productsImageDto = productImageService.findAllProductsImageDto();
		if (productsImageDto.isEmpty()) {
			return new ResponseEntity<List<ProductImageDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductImageDto>>(productsImageDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductImageDto> getProduct(@PathVariable("id") long id) {
		System.out.println("Fetching User with id " + id);

		ProductImageDto productimageDto = productImageService.findProductImageDtoById(id);
		if (productimageDto == null) {
			System.out.println("Product with id " + id + " not found");
			return new ResponseEntity<ProductImageDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductImageDto>(productimageDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ProductImageDto> deleteSupplier(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		
		if (productImageService.findProductImageDtoById(id)==null) {
			System.out.println("Unable to delete. supplier with id " + id + " not found");
			return new ResponseEntity<ProductImageDto>(HttpStatus.NOT_FOUND);
		}
		
		productImageService.deleteProductImage(id);
		return new ResponseEntity<ProductImageDto>(HttpStatus.NO_CONTENT);
	}
}

