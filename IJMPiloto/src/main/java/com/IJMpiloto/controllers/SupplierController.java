package com.IJMpiloto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.IJMpiloto.entities.Supplier;
import com.IJMpiloto.services.SupplierService;


@RestController
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	@RequestMapping("/register")
	public ModelAndView register()
	{
		ModelAndView model = new ModelAndView("/supplier/form_register");
		return model;
	}
	@RequestMapping("/list")
	public ModelAndView list()
	{
		ModelAndView model = new ModelAndView("/supplier/list");
		List<Supplier> suppliers = supplierService.findAll();
        model.addObject("suppliers",suppliers);
		return model;
	}
	

	
	//create a supplier
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> createSupplier(@RequestBody Supplier supplier)
	{
		
        if (supplierService.isSupplierExist(supplier)) {
            System.out.println("A User with name " + supplier.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        supplierService.addSupplier(supplier);
        System.out.println("A User with name " + supplier.getName() + " has been added");
        return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
    //update a supplier
	 @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Supplier> updateUser(@PathVariable("id") long id, @RequestBody Supplier supplier) {
	        System.out.println("Updating Supplier " + id);
	         
	        Supplier currentSupplier = supplierService.findById(id);
	         
	        if (currentSupplier==null) {
	            System.out.println("Supplier with id " + id + " not found");
	            return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentSupplier.setName(supplier.getName());
	        currentSupplier.setCode(supplier.getCode());
	         
	        supplierService.updateSupplier(currentSupplier);
	        return new ResponseEntity<Supplier>(currentSupplier, HttpStatus.OK);
	    }
	
	//retrieve data from all suppliers
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Supplier>> listAllUsers() {
        List<Supplier> suppliers = supplierService.findAll();
        if(suppliers.isEmpty()){
            return new ResponseEntity<List<Supplier>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Supplier>>(suppliers, HttpStatus.OK);
    }
    
	//retrieve data from a supplier
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Supplier> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Supplier supplier = supplierService.findById(id);
        if (supplier == null) {
            System.out.println("Supplier with id " + id + " not found");
            return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
    }
	
	//delete a supplier
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Supplier> deleteSupplier(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        Supplier supplier = supplierService.findById(id);
        if (supplier == null) {
            System.out.println("Unable to delete. supplier with id " + id + " not found");
            return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
        }
 
        supplierService.deleteSupplier(supplier);
        return new ResponseEntity<Supplier>(HttpStatus.NO_CONTENT);
    }		
}
