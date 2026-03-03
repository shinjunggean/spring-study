package org.zerock.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder	// 필요한 데이터만을 추가해서 객체를 생성할 수 있는 방법을 제공
@AllArgsConstructor
@NoArgsConstructor
public class ReplyListPagingDTO {
	private List<ReplyDTO> replyDTOList; // 댓글 목록
	private int totalCount; // 전체 데이터의 개수
	private int page; // 현재 페이지 번호
	private int size; // 한 페이지당 크기(한 페이지에 출력되는 데이터 개수)
	
	// 페이지 번호의 처리 계산
	private int start; // 시작 번호
	private int end; // 마지막 번호
	private boolean prev;
	private boolean next;
	private List<Integer> pageNums;
	
	public ReplyListPagingDTO(List<ReplyDTO> replyDTOList, int totalCount, int page, int size) {
		this.replyDTOList = replyDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;
		
		// start 계산을 위한 end 페이지
		int tempEnd = (int) (Math.ceil(page / 10.0)) * 10; // 여기서 10은 페이지 번호 블록 크기(blockSize)
		
		this.start = tempEnd - 9;
		
		this.prev = start != 1; // start 값이 1이 아니라면 이전 페이지로 이동 필요
		
		int lastPage = (int) Math.ceil(totalCount / (double) size);
		this.end = Math.min(tempEnd, lastPage);
		
		this.next = end < lastPage;
		
		// 화면에 출력할 번호들 계산
		this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
	}
}
