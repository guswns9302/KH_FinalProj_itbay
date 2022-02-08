package web.com.itbay.product.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {

	@Autowired
	SqlSession session;
	
	public List<ProductDTO> selectProduct(ProductDTO productDto) {
		List<ProductDTO> dtoList = this.session.selectList("ProductMapper.selectProduct", productDto);
		
		return dtoList;
	}
	
	public ProductDTO selectProductDetail(ProductDTO productDto) {
		ProductDTO productDetail = this.session.selectOne("ProductMapper.selectProductDetail", productDto);
		
		return productDetail;
	}
}
