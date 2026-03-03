package org.zerock.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder	// 필요한 데이터만을 추가해서 객체를 생성할 수 있는 방법을 제공
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
	private Long rno;
	private String replyText;
	private String replyer;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime replyDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateDate;
	private boolean delflag;
	
	private Long bno;
}
