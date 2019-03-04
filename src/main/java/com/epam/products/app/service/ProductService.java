package com.epam.products.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.products.app.dto.ProductDTO;
@Service
public interface ProductService {
	public List<ProductDTO> getAllProducts();
	public boolean addProduct(ProductDTO productDTO);
	public void updateProduct(ProductDTO productDTO);
	public void deleteProduct(Long productId);
}
