package web.com.itbay.product.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	ProductDAO productDao;
	
	public List<ProductDTO> selectProduct(String subject, String sold_out) {

		ProductDTO productDto = new ProductDTO();
		productDto.setSubject(subject);
		productDto.setSold_out(sold_out);

		return productDao.selectProduct(productDto);

	}
	
	public ProductDTO selectProductDetail(int id) {
		
		ProductDTO productDto = new ProductDTO();
		productDto.setId(id);
		
		return productDao.selectProductDetail(productDto);
		
	}

}
