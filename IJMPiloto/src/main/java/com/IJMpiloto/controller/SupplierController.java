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

import com.IJMpiloto.model.Supplier;
import com.IJMpiloto.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Supplier>> listAllUsers() {
		List<Supplier> suppliers = supplierService.findAllSuppliers();
		if (suppliers.isEmpty()) {
			// You many decide to return HttpStatus.NOT_FOUND
			return new ResponseEntity<List<Supplier>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Supplier>>(suppliers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching User with id " + id);
		Supplier supplier = supplierService.findSupplierById(id);
		if (supplier == null) {
			System.out.println("Supplier with id " + id + " not found");
			return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createSupplier(@RequestBody Supplier supplier,UriComponentsBuilder ucBuilder) {

        System.out.println("Creating Supplier " + supplier.getName());
        
        if (supplierService.isSupplierExist(supplier)) {
            System.out.println("A Supplier with name " + supplier.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        supplierService.saveSupplier(supplier);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/supplier/{id}").buildAndExpand(supplier.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateSupplier(@PathVariable("id") long id, @RequestBody Supplier supplier) {
		System.out.println("Updating Supplier " + id);
		HttpStatus status;
		try {
			supplier.setId(id);
			supplierService.updateSupplier(supplier);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<Void>(status);
	}



	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Supplier> deleteSupplier(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		Supplier supplier = supplierService.findSupplierById(id);
		if (supplier == null) {
			System.out.println("Unable to delete. supplier with id " + id + " not found");
			return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
		}

		supplierService.deleteSupplier(supplier);
		return new ResponseEntity<Supplier>(HttpStatus.NO_CONTENT);
	}
}