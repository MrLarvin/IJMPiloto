package com.IJMpiloto.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.IJMpiloto.dao.SupplierDao;
import com.IJMpiloto.model.Product;
import com.IJMpiloto.model.Supplier;

@Test(groups = { "IJMPiloto", "Service", "SupplierService" })
public class SupplierServiceImplTest {

	@Mock
	private SupplierDao supplierDao;

	@InjectMocks
	private SupplierServiceImpl service;

	private Supplier supplier;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		fillDummyData();
	}

	@BeforeMethod
	public void resetMocks() {
		Mockito.reset(supplierDao);
	}

	@Test
	public void saveSupplierTest() throws Exception {
		Mockito.doNothing().when(supplierDao).save(Matchers.any(Supplier.class));

		service.saveSupplier(supplier);

		Mockito.verify(supplierDao, Mockito.times(1)).save(Matchers.any(Supplier.class));
	}

	@Test
	public void updateSupplierTest() throws Exception {
		Mockito.when(supplierDao.findById(Matchers.anyLong())).thenReturn(supplier);

		service.updateSupplier(supplier);

		Mockito.verify(supplierDao, Mockito.times(1)).findById(Matchers.anyLong());
	}

	@Test
	public void deleteSupplierTest() throws Exception {
		Mockito.doNothing().when(supplierDao).delete(Matchers.any(Supplier.class));

		service.deleteSupplier(supplier);

		Mockito.verify(supplierDao, Mockito.times(1)).delete(Matchers.any(Supplier.class));
	}

	@Test
	public void findSupplierByIdTest() throws Exception {
		Mockito.when(supplierDao.findById(Matchers.anyLong())).thenReturn(supplier);

		Supplier s = service.findSupplierById(Matchers.anyLong());

		Assert.assertEquals(supplier, s);
		Mockito.verify(supplierDao, Mockito.times(1)).findById(Matchers.anyLong());
	}
	
	@Test
	public void findAllSuppliersTest() throws Exception {
		ArrayList<Supplier> suppliers = new ArrayList<>();
		suppliers.add(supplier);
		Mockito.when(supplierDao.findAll()).thenReturn(suppliers);
		
		List<Supplier> response = service.findAllSuppliers();
		
		Assert.assertFalse(response.isEmpty());
		Assert.assertEquals(1, response.size());
		Assert.assertEquals(response.get(0), suppliers.get(0));
		Mockito.verify(supplierDao, Mockito.times(1)).findAll();
	}
	
	@Test
	public void isSupplierExist_NotExistTest() throws Exception {
		List<Supplier> suppliers = new ArrayList<>();
		
		Mockito.when(supplierDao.findByCode(Matchers.anyString())).thenReturn(suppliers);
		Mockito.when(supplierDao.findByName(Matchers.anyString())).thenReturn(suppliers);
		
		boolean response = service.isSupplierExist(supplier);
		
		Assert.assertFalse(response);
		Mockito.verify(supplierDao, Mockito.times(1)).findByCode(Matchers.anyString());
		Mockito.verify(supplierDao, Mockito.times(1)).findByName(Matchers.anyString());
	}
	
	@Test
	public void isSupplierExist_ExistTest() throws Exception {
		List<Supplier> suppliers = new ArrayList<>();
		suppliers.add(new Supplier());
		
		Mockito.when(supplierDao.findByCode(Matchers.anyString())).thenReturn(suppliers);
		Mockito.when(supplierDao.findByName(Matchers.anyString())).thenReturn(suppliers);
		
		boolean response = service.isSupplierExist(supplier);
		
		Assert.assertTrue(response);
		Mockito.verify(supplierDao, Mockito.times(1)).findByCode(Matchers.anyString());
		Mockito.verify(supplierDao, Mockito.never()).findByName(Matchers.anyString());
	}
	
	@Test(enabled = false)
	public void findSupplierProductsByIdTest() throws Exception {
		
	}
	
	private void fillDummyData() {
		Product p1 = new Product();
		p1.setCode("ProductCode_01");
		p1.setDescription("ProductDescription_01");
		Set<Product> products = new HashSet<>();
		products.add(p1);

		supplier = new Supplier();
		supplier.setCode("SupplierCode_01");
		supplier.setName("SupplierName_01");
		supplier.setProducts(products);
	}
}