package web.com.itbay.product.model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import web.com.itbay.PagingUtil;
import web.com.itbay.img.model.ImgDAO;
import web.com.itbay.img.model.ImgDTO;
import web.com.itbay.product.controller.ProductWriteController;

@Service
public class ProductService {
	
	@Autowired
	ProductDAO productDao;
	
	@Autowired
	ImgDAO imgDao;
	
	
	private static String uploadPath = "C:\\Users\\user\\git\\KH_FinalProj_itbay\\itbay\\src\\main\\webapp\\resources\\img\\";
	
	public List<ProductDTO> selectProduct(String subject, String sold_out, int page, Model model) {

		ProductDTO productDto = new ProductDTO();
		productDto.setSubject(subject);
		productDto.setSold_out(sold_out);
		
		int count = productDao.selectProductCount(productDto);
		
		PagingUtil paging = new PagingUtil(count, page);
		
		
		productDto.setStartIndex(paging.getStartIndex());
		productDto.setEndIdex(paging.getEndIndex());
		
		model.addAttribute("paging", paging);
		
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
			String path = uploadFile(file.get(i).getOriginalFilename(), file.get(i).getBytes());
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
	
	
	//업로드된 파일을 저장하는 함수
	public static String uploadFile(String originalName, byte[] fileDate) throws IOException {
		
		UUID uid = UUID.randomUUID();
		
		String savedName = uid.toString() + "_" + originalName;

		
		File target = new File(uploadPath, savedName);
		makeDir(uploadPath);
		//org.springframework.util 패키지의 FileCopyUtils는 파일 데이터를 파일로 처리하거나, 복사하는 등의 기능이 있다.
		FileCopyUtils.copy(fileDate, target);
		
		return savedName;
		
	}
	
	
	//폴더 생성 함수
	private static void makeDir(String uploadPath) {
		
		if(new File(uploadPath).exists()) {
			return;
		}
		

			
		File dirPath = new File(uploadPath);
		
		if(!dirPath.exists()) {
			dirPath.mkdir();
		}

	}

	public void productUpdate(ProductDTO productDto) {
		
		productDao.productUpdate(productDto);
	}

	public void productDelete(int id) {
		imgDao.deleteImg(id);
		productDao.productDelete(id);
	}
	
	

}
