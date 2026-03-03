package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.BoardListPagingDTO;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class BoardService {
	
	private final BoardMapper boardMapper;
	
	
	// 게시물 목록 처리 : BoardMapper에서 나온 BoardDTO 목록을 반환하도록 작성
	public List<BoardDTO> getList(){
		return boardMapper.list();
	}
	
	public BoardListPagingDTO getList(int page, int size, String typeStr, String keyword){
		page = page <= 0 ? 1 : page;
		size = (size < 10 || size > 100) ? 10 : size;
		
		int skip = (page - 1) * size;
		
		// 검색관련추가
		String[] types = (typeStr != null && !typeStr.isEmpty()) ? typeStr.split("") : null;
		
		List<BoardDTO> list = boardMapper.listSearch(skip, size, types, keyword);
		
		int total = boardMapper.listCountSearch(types, keyword);
		
		return new BoardListPagingDTO(list, total, page, size, typeStr, keyword);
	}
	
	
	// 게시글 등록 처리: 등록 기능을 작성하고 새로 추가된 게시물의 번호를 반환하도록 구성
	public Long register(BoardDTO boardDTO) {
		int insertCount = boardMapper.insert(boardDTO);
		
		log.info("insertCount: " + insertCount);
		
		return boardDTO.getBno();
	}
	
	// 게시물 조회 처리: 파라미터는 게시물의 번호(bno)이고 리턴 타입은 BoardDTO를 사용
	public BoardDTO read(Long bno) {
		BoardDTO boardDTO = boardMapper.selectOne(bno);
		
		return boardDTO;
	}
	
	// 게시물 삭제 처리: 삭제하고자 하는 게시물의 번호(bno)를 이용해서 Mapper의 remove() 호출
	public void remove(Long bno) {
		boardMapper.remove(bno);
	}
	
	// 게시물 수정 처리: 수정에 필요한 데이터는 제목(title), 내용(content), 삭제여부(delFalg), 게시물 번호(bno)가 필요
	// 관련있는 여러 개의 데이터이므로 BoardDTO로 처리
	public void modify(BoardDTO boardDTO) {
		boardMapper.update(boardDTO);
	}
	
	
}
