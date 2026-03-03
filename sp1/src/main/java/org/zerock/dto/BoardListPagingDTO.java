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
public class BoardListPagingDTO {
	private List<BoardDTO> boardDTOList;
	private int totalCount;
	private int page;
	private int size;
	
	// 페이지 번호의 처리 계싼
	private static final int BLOCK_SIZE = 10;	// 페이지 번호 블록 크기
	private int start;
	private int end;
	private boolean prev;
	private boolean next;
	private List<Integer> pageNums;
	
	// 검색 관련
	private String types;
	private String keyword;
	
	
	public BoardListPagingDTO(List<BoardDTO> boardDTOList, int totalCount, int page, int size, String types, String keyword) {
		super();
		this.boardDTOList = boardDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;
		
		this.boardDTOList = boardDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;
		// 검색 관련 추가
		this.types = types;
		this.keyword = keyword;
		
		// start 계산을 위한 tempEnd 페이지
		int tempEnd = (int) (Math.ceil(page / (double) BLOCK_SIZE)) * BLOCK_SIZE;
		this.start = tempEnd - (BLOCK_SIZE - 1);
		
		this.prev = start != 1; // start 값이 1이 아니라면 이전 페이지로 이동 필요
		
		// 정확한 end 페이지 번호 계산
		int lastPage = (int) Math.ceil(totalCount / (double) size);
		this.end = Math.min(tempEnd, lastPage);
		
		// 현재 블록의 마지막 페이지(end)가 전체 마지막 페이지(lastPage)보다 작으면 다음 블록 존재(next로 이동 가능)
		this.next = end < lastPage;
		
		// 화면에 출력할 번호들 계산
		this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
	}
	
	
}

