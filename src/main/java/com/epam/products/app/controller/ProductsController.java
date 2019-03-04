package com.epam.products.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.epam.products.app.dto.ProductDTO;
import com.epam.products.app.exception.ProductNotFoundException;
import com.epam.products.app.service.ProductService;
@RestController
public class ProductsController implements ProductsApi {
@Autowired
private ProductService productService; 
	@Override
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<ProductDTO> products = null;
		try {
			products = productService.getAllProducts();
		}catch (ProductNotFoundException e) {
			
			return ResponseEntity.ok(null);
		}
	return ResponseEntity.ok(products);
	}

	@Override
	public ResponseEntity<Object> addProduct(ProductDTO productDTO) {
		boolean isAdded = productService.addProduct(productDTO);
		if(isAdded) {
			return ResponseEntity.ok(HttpStatus.CREATED.value());
		}
		return ResponseEntity.badRequest().build();
	}

	@Override
	public ResponseEntity<Object> updateProduct(Long productId, ProductDTO productDTO) {
		try {
			productDTO.setProductId(productId);
			productService.updateProduct(productDTO);
			
		}catch (ProductNotFoundException e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(HttpStatus.CREATED.value());
	}

	@Override
	public ResponseEntity<Object> deleteProduct(Long productId) {
		try {
			productService.deleteProduct(productId);
		}catch (ProductNotFoundException e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(HttpStatus.OK.value());
	}

}
