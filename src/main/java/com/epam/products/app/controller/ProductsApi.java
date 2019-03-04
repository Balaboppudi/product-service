package com.epam.products.app.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.products.app.dto.ProductDTO;

public interface ProductsApi {
	@GetMapping(value="/products")
	public ResponseEntity<List<ProductDTO>> getAllProducts();
	@PostMapping(value="/products", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addProduct(@RequestBody ProductDTO productDTO);
	@PutMapping(value="/products/{productId}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateProduct(@PathVariable("productId") Long productId,@RequestBody ProductDTO productDTO);
	@DeleteMapping(value="/products/{productId}")
	public ResponseEntity<Object> deleteProduct(@PathVariable("productId") Long productId);
	
}
