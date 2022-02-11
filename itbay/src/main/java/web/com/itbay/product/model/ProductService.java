package web.com.itbay.product.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import web.com.itbay.UploadFileUtils;
import web.com.itbay.img.model.ImgDTO;
import web.com.itbay.product.controller.ProductWriteController;

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
	
	public void productSave(ProductDTO productDto, List<MultipartFile> file) throws Exception{
		
	
		
		productDao.saveProduct(productDto);

		for(int i=0; i<file.size(); i++) {
			String path = UploadFileUtils.uploadFile(file.get(i).getOriginalFilename(), file.get(i).getBytes());
			ImgDTO dto = new ImgDTO();
			dto.setImg_name(path);
			dto.setProduct_id(productDto.getId());
			if(i==0) {
				dto.setR_img("Y");
			} else {
				dto.setR_img("N");
			}
			productDao.saveImg(dto);
		}
		
	
		
	
	}

}
