package com.svscorp.web.fullstack.controller;

import com.svscorp.web.fullstack.model.InvoiceDetails;
import com.svscorp.web.fullstack.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/web")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/products")
	public List<InvoiceDetails> findAllProducts() throws IOException {
		return service.getProducts();
	}



	@GetMapping("/productById/{id}")
	public InvoiceDetails findProductById(@PathVariable String id) {
		return service.getProductById(id);
	}

	@GetMapping("/reports/{Date1}/{Date2}")
	public List<InvoiceDetails> getreport(@PathVariable String Date1, @PathVariable String Date2) throws IOException {
		return service.getReports(Date1, Date2);
	}

	@PostMapping("/addProduct")
	public InvoiceDetails addProduct(@RequestBody InvoiceDetails product) {
		return service.saveProduct(product);
	}

	@PutMapping("/update/{id}")
	public InvoiceDetails updateProduct(@RequestBody InvoiceDetails product, @PathVariable String id) {
		return service.updateProduct(product, id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable String id) {
		return service.deleteProduct(id);
	}
}