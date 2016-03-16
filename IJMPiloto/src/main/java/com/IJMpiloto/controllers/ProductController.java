package com.IJMpiloto.controllers;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.IJMpiloto.entities.Product;
import com.IJMpiloto.services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@RequestMapping("/register")
	public ModelAndView register()
	{
		ModelAndView model = new ModelAndView("/product/form_register");
		return model;
	}
	@RequestMapping(value="/submit",method=RequestMethod.POST)
	public ModelAndView submit(@Valid @ModelAttribute("product1") Product product1, BindingResult result )
	{
		if(result.hasErrors())
		{
			ModelAndView model = new ModelAndView("/product/form_register");
			model.addObject("message","Un error ha ocurrido");
			return model;
		}
		//ASIGNAR HOMECONTROLLER PARA LAS SIGUIENTES ACCIONES
		productService.addProduct(product1);
		ModelAndView model = new ModelAndView("home");	
		return model;
	}

	//create a Product
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> createSupplier(@RequestBody Product product)
	{
		
        if (productService.isProductExist(product)) {
            System.out.println("A User with name " + product.getDescription() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        productService.addProduct(product);
        System.out.println("A User with name " + product.getDescription() + " has been added");
        return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
    //update a supplier
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateUser(@PathVariable("id") long id, @RequestBody Product product) {
	        System.out.println("Updating Supplier " + id);
	         
	        Product currentProduct = productService.findById(id);
	         
	        if (currentProduct==null) {
	            System.out.println("Supplier with id " + id + " not found");
	            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentProduct.setDescription(product.getDescription());
	        currentProduct.setCode(product.getCode());
	         
	        productService.updateProduct(currentProduct);
	        return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
	    }
	
	//retrieve data from all suppliers
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listAllUsers() {
        List<Product> products = productService.findAll();
        if(products.isEmpty()){
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
    
	//retrieve data from a supplier
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Product product = productService.findById(id);
        if (product == null) {
            System.out.println("Supplier with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
	
	//delete a supplier
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteSupplier(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        Product product = productService.findById(id);
        if (product == null) {
            System.out.println("Unable to delete. supplier with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
 
        productService.deleteProduct(product);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }		
}
