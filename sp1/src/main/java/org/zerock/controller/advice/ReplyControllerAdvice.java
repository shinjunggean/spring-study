package org.zerock.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zerock.service.exception.ReplyException;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice(basePackages = "org.zerock.reply") // 해당 패키지의 컨트롤러에만 적용
@Log4j2
public class ReplyControllerAdvice {
	
	// 특정한 타입의 예외가 발생할 때 동작하도록 설정
	@ExceptionHandler(ReplyException.class)
	public ResponseEntity<String> handleReplyError(ReplyException ex){
		log.error(ex.getMessage());
		
		return ResponseEntity.status(ex.getCode()).body(ex.getMessage());
		
		
	}
}
