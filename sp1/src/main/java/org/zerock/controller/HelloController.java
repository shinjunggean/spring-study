package org.zerock.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.SampleDTO;
import org.zerock.service.HelloService;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Controller	// 해당 클래스의 객체가 스프링에서 빈(Bean)으로 관리되는 대상임을 지정
@RequiredArgsConstructor	// 생성자를 통한 의존성 주입을 할 수 있도록
@ToString
@Log4j2
@RequestMapping("/sample")	// '/sample'로 시작하는 요청을 HelloController가 처리한다
public class HelloController {
	private final HelloService helloService;
	
//	@Autowired	//생성자 하나만 있을땐 생략 가능
//	public HelloController(HelloService helloService) {
//		this.helloService = helloService;
//	}
	
//	@RequestMapping(value = "/ex1", method = RequestMethod.GET)	// 옛 방식
	// 스프링 4.3 부터는 @@GetMapping, @PostMapping, @PutMapping, @DeleteMapping 등 지원
	@GetMapping("/ex1")
	public void ex1() {
		log.info("/sample/ex1");
	}
	// 메소드가 void이면 스프링이 요청 URL 기반으로 뷰 이름을 추론
	// 즉, 사용된 요청 경로 = 뷰 이름
	
	@GetMapping("/ex2")
	public String ex2() {
		log.info("/sample/ex2");
		
		return "sample/success";
	}
	
	@GetMapping("/ex3")
	public String ex3() {
		log.info("/sample/ex3");
		
		return "sample/ex3re";
	}
	
	@GetMapping("/ex4")
	public void ex4(
				@RequestParam(name = "n1", defaultValue = "1") int num,
//				@RequestParam(name = "name") String name
				@RequestParam("name") String name) {	// 속성이 하나인 경우 그냥 문자열로 작성 가능
		log.info("/sample/ex4");
		log.info("/num: " + num);
		log.info("name: " + name);
	}
	
	@GetMapping("/ex5")
	public void ex5(SampleDTO dto) {	// 객체형은 별도의 어노테이션 없이 선언
		log.info("/sample/ex5");
		log.info(dto);
	}
	
	@GetMapping("/ex6")
	public void ex6(Model model) {	
		model.addAttribute("name", "Hong Gil Dong");
		model.addAttribute("age", "23");
	}
	
	@GetMapping("/ex7")
	public String ex7(RedirectAttributes ratt) {
		ratt.addAttribute("name", "Hong");
		ratt.addFlashAttribute("age", 16); // 1회성 데이터 전달용
		// 참고: 내부적으로 세션에 임시 저장 후, 다음 요청에서 Model에 자동 주입되고 즉시 제거
		return "redirect:/sample/ex8";
		
	}
	
	@GetMapping("/ex8")
	public void ex8() {	
		log.info("/sample/ex8");
		
	}
}
