package web.com.itbay.product.model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import web.com.itbay.PagingUtil;
import web.com.itbay.cart.model.CartDAO;
import web.com.itbay.cart.model.CartDTO;
import web.com.itbay.img.model.ImgDAO;
import web.com.itbay.img.model.ImgDTO;
import web.com.itbay.members.model.MembersDTO;

@Service
public class ProductService {
	
	@Autowired
	ProductDAO productDao;
	
	@Autowired
	ImgDAO imgDao;
	
	@Autowired
	CartDAO cartDao;
	
	
	private static String uploadPath = "C:\\Users\\user\\git\\KH_FinalProj_itbay\\itbay\\src\\main\\webapp\\resources\\img\\";
	
	public List<ProductDTO> selectProduct(String subject, String sold_out, int page, Model model) {

		ProductDTO productDto = new ProductDTO();
		productDto.setSubject(subject);
		productDto.setSold_out(sold_out);
		
		// 같은조회조건의 총 갯수를 가져온다.
		// 가져오는 이유는 총갯수를 가지고 페이지에 뿌려질 페이징번호를 계산해야하기 때문
		// EX) 총50개의 총갯수라면 한페이지당 10건씩 뿌려진다고 가정을 하고 50/10 = 5 <- 이게 jsp에 보여줄 총 페이지 갯수 
		int count = productDao.selectProductCount(productDto);
		
		PagingUtil paging = new PagingUtil(count, page);
		
		
		productDto.setStartIndex(paging.getStartIndex());
		productDto.setEndIdex(paging.getEndIndex());
		
		model.addAttribute("paging", paging);
		
		return productDao.selectProduct(productDto);

	}
	
	public ProductDTO selectProductDetail(int id, HttpServletRequest request, Model model, HttpSession session) {
		
		ProductDTO productDto = new ProductDTO();
		productDto.setId(id);
		
		session = request.getSession();
		MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
	
		
		
		if(loginData != null) {
			CartDTO cartDto = new CartDTO();
			cartDto.setMembers_id(loginData.getId());
			cartDto.setProduct_id(id);
			model.addAttribute("loginData",loginData);
			int cartCount = cartDao.selectMemberCart(cartDto);
			model.addAttribute("cartCount", cartCount);
		}		
		
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

	public List<ProductDTO> selectRecomend() {
		List<ProductDTO> recomend = productDao.selectRecomendProduct();
		return recomend;
	}

	public List<ProductDTO> selectViewCount() {
		List<ProductDTO> viewCount = productDao.selectViewCountProduct();
		return viewCount;
	}
	
	

}
