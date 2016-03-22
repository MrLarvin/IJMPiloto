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


import com.IJMpiloto.dto.ProductDto;
import com.IJMpiloto.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	// create a Product
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) {
		if (productService.isProductExist(productDto.getCode())) {
			System.out.println("A User with name " + productDto.getDescription() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		productService.saveProduct(productDto);
		System.out.println("A User with name " + productDto.getDescription() + " has been added");
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProduct(@PathVariable("code") String code, @RequestBody ProductDto productDto) {
		System.out.println("Updating Product " + code);
		HttpStatus status;
		try {
			productService.updateProduct(productDto, code);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<Void>(status);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProductDto>> listAllProducts() {
		List<ProductDto> products = productService.findAllProductsDto();
		if (products.isEmpty()) {
			return new ResponseEntity<List<ProductDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public ResponseEntity<ProductDto> getProduct(@PathVariable("code") String code) {
		System.out.println("Fetching User with code " + code);

		ProductDto productDto = productService.findProductDtoByCode(code);
		if (productDto == null) {
			System.out.println("Product with code " + code + " not found");
			return new ResponseEntity<ProductDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<ProductDto> deleteSupplier(@PathVariable("code") String code) {
		System.out.println("Fetching & Deleting User with id " + code);

		
		if (!productService.isProductExist(code)) {
			System.out.println("Unable to delete. supplier with code " + code + " not found");
			return new ResponseEntity<ProductDto>(HttpStatus.NOT_FOUND);
		}
		
		productService.deleteProduct(code);
		return new ResponseEntity<ProductDto>(HttpStatus.NO_CONTENT);
	}
}
