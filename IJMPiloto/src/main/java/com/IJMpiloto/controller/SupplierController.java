package com.IJMpiloto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.IJMpiloto.dto.SupplierDto;
import com.IJMpiloto.model.Supplier;
import com.IJMpiloto.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SupplierDto>> listAllUsers() {
		List<SupplierDto> suppliers = supplierService.findAllSuppliersDto();
		if (suppliers.isEmpty()) {
			// You many decide to return HttpStatus.NOT_FOUND
			return new ResponseEntity<List<SupplierDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SupplierDto>>(suppliers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SupplierDto> getUser(@PathVariable("code") String code) {
		System.out.println("Fetching Supplier with code " + code);
		SupplierDto supplierDto = supplierService.findSupplierDtoByCode(code);
		if (supplierDto == null) {
			System.out.println("Supplier with code " + code + " not found");
			return new ResponseEntity<SupplierDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SupplierDto>(supplierDto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createSupplier(@RequestBody SupplierDto supplierDto,UriComponentsBuilder ucBuilder) {

        System.out.println("Creating Supplier " + supplierDto.getName());
        
        if (supplierService.isSupplierExist(supplierDto.getCode())) {
            System.out.println("A Supplier with name " + supplierDto.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        supplierService.saveSupplier(supplierDto);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/supplier/{code}").buildAndExpand(supplierDto.getCode()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateSupplier(@PathVariable("code") String code, @RequestBody SupplierDto supplierDto) {
		System.out.println("Updating Supplier " + code);
		HttpStatus status;
		try {
			supplierService.updateSupplier(supplierDto,code);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<Void>(status);
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<Supplier> deleteSupplier(@PathVariable("code") String code) {
		System.out.println("Fetching & Deleting User with code " + code);

		Supplier supplier = supplierService.findSupplierByCode(code);
		if (supplier == null) {
			System.out.println("Unable to delete. supplier with code " + code + " not found");
			return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
		}

		supplierService.deleteSupplier(supplier);
		return new ResponseEntity<Supplier>(HttpStatus.NO_CONTENT);
	}
}