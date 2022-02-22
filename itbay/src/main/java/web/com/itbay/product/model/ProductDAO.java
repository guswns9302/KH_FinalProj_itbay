package web.com.itbay.product.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.com.itbay.img.model.ImgDTO;

@Repository
public class ProductDAO {

	@Autowired
	SqlSession session;
	
	public List<ProductDTO> selectProduct(ProductDTO productDto) {
		List<ProductDTO> dtoList = this.session.selectList("ProductMapper.selectProduct", productDto);
		
		return dtoList;
	}
	
	public int selectProductCount(ProductDTO productDto) {
		int count = this.session.selectOne("ProductMapper.selectProductCount", productDto);
		
		return count;
	}
	
	public ProductDTO selectProductDetail(ProductDTO productDto) {
		ProductDTO productDetail = this.session.selectOne("ProductMapper.selectProductDetail", productDto);
		
		return productDetail;
	}
	
	public int saveProduct(ProductDTO productDto) {
		return this.session.insert("ProductMapper.saveProduct", productDto);
	}
	
	public int saveImg(ImgDTO imgDto) {
		return this.session.insert("ProductMapper.saveImage", imgDto);
	}

	public void productUpdate(ProductDTO productDto) {
		this.session.update("ProductMapper.updateProduct", productDto);
		
	}

	public void productDelete(int id) {
		this.session.delete("ProductMapper.deleteProduct", id);
		
	}

	public List<ProductDTO> selectRecomendProduct() {
		List<ProductDTO> recomend = this.session.selectList("ProductMapper.selectRecomend");
		return recomend;
	}

	public List<ProductDTO> selectViewCountProduct() {
		List<ProductDTO> viewCount = this.session.selectList("ProductMapper.selectViewCount");
		return viewCount;
	}

	public void productUpdateViewCnt(int id) {
		this.session.update("ProductMapper.updateViewCnt", id);
		
	}
}
