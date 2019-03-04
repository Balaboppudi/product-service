package com.epam.products.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.epam.products.app.converter.ProductsConverter;
import com.epam.products.app.dto.ProductDTO;
import com.epam.products.app.exception.ProductNotFoundException;
import com.epam.products.app.repository.Product;
import com.epam.products.app.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService {
	org.slf4j.Logger logger = (org.slf4j.Logger) LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductsConverter productsConverter;

	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> products = productRepository.findAll();
		if (CollectionUtils.isEmpty(products)) {
			logger.info("Could not find any products in the system");
		} else {
			logger.info("Found products in the system");
		}
		List<ProductDTO> productDTO = productsConverter.converToProductDTO(products);
		logger.info("Found products in the system of size {}", productDTO.size());

		return productDTO;
	}

	@Override
	public boolean addProduct(ProductDTO productDTO) {
		Product model = productsConverter.convertToModel(productDTO);
		productRepository.save(model);
		return true;
	}

	@Override
	@Transactional
	public void updateProduct(ProductDTO productDTO) {
		Optional<Product> product = productRepository.findById(productDTO.getProductId());
		if (!product.isPresent()) {
			logger.error("Product not found {}", productDTO.getProductId());
			throw new ProductNotFoundException();
		} else {
			Product productToUpdate = product.get();
			productToUpdate.setName(productDTO.getName());
			productRepository.save(productToUpdate);
			logger.error("Product updated {}", productDTO.getProductId());

		}

	}

	@Override
	public void deleteProduct(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (!product.isPresent()) {
			logger.error("Product not found {}", productId);
			throw new ProductNotFoundException();
		} else {
			productRepository.delete(product.get());
			logger.info("Product deleted {}", productId);
		}
	}

}
