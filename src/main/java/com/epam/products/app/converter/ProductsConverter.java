package com.epam.products.app.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.epam.products.app.dto.ProductDTO;
import com.epam.products.app.repository.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@SuppressWarnings("unchecked")
public class ProductsConverter {
	
	public List<ProductDTO> converToProductDTO(List<Product> products){
		ObjectMapper mapper= new ObjectMapper();
		List<ProductDTO> convertValue = mapper.convertValue(products, List.class);
		 return convertValue;
	}

	public Product convertToModel(ProductDTO productDTO) {
		ObjectMapper mapper= new ObjectMapper();
		Product product= mapper.convertValue(productDTO, Product.class);
		return product;
	}
}
