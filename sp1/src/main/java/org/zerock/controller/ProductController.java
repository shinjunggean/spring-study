package org.zerock.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.ProductDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/product")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

	@GetMapping("/register")
	public void registerGET() {
		log.info("product register");
	}
	
	@PostMapping("/register")
	public String register(
			ProductDTO productDTO,
			@RequestParam("files") MultipartFile[] files,
			RedirectAttributes rttr) {
		log.info("-------------------------");
		
		log.info(productDTO);
		log.info(files);
		
		return "redirect:/product/list";
	}
	
	// 파일 업로드 기능
	private List<String> uploadFiles(MultipartFile[] files)  {	// throws 생략 가능
		List<String> uploadNames = new ArrayList<>();
		
		// 파일을 첨부하지 않은 상태로 전송하면, files 배열 자체는 null이 아니고 길이가 1인 배열로 들어옴
		log.info("MultipartFile 배열 길이: " + files.length);	// 확인용
		
		if(files == null || files.length == 0) {
			return uploadNames;
		}
		
		String uploadPath = "C:\\upload";
		
		log.info("------------------- uploadPath");
		log.info(uploadPath);
		
		// (참고) 업로드 디렉토리 생성 보장
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		
		
		return null;
	}
	
	
}
