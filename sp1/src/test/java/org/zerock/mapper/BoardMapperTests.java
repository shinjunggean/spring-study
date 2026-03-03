package org.zerock.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.BoardDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")	// 테스트에 필요한 설정을 로딩하기 위해 설정
@Log4j2
public class BoardMapperTests {

	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testInsert2() {
		BoardDTO boardDTO = BoardDTO.builder()
				.title("title")
				.content("content")
				.writer("user00")
				.build();
		
		int insertCount = boardMapper.insert(boardDTO);
		
		log.info("-------------------");
		log.info("insertCount: " + insertCount);
		
		log.info("=================================");
		log.info("BNO: " + boardDTO.getBno());
	}
	
	@Test
	public void testSelectOne() {
		Long bno = 2L;
		
		BoardDTO boardDTO = boardMapper.selectOne(bno);
		
		log.info("board: " + boardDTO);
		
	}
	
	@Test
	public void testRemove() {
		Long bno = 2L;
		
		int removeCount = boardMapper.remove(bno);
		
		log.info("-----------------------------");
		log.info("removeCount: " + removeCount);
	}
	
	@Test
	public void testUpdate() {
		BoardDTO boardDTO = BoardDTO.builder()
				.bno(2L)
				.title("Update title")
				.content("Update content")
				.delFlag(false)
				.build();
		
		int updateCount = boardMapper.update(boardDTO);
		
		log.info("--------------------------");
		log.info("updateCount: " + updateCount);
	}
	
	@Test
	public void testList() {
		List<BoardDTO> dtoList = boardMapper.list();
		
		log.info("dtoList");
		log.info(dtoList);
		
		dtoList.stream().forEach(board -> log.info(board));
	}
	@Test
	public void testList2() {
		int page = 2;
		int count = 10;
		int skip = (page - 1) * count;
		
		List<BoardDTO> dtoList = boardMapper.list2(skip, count);
		
		log.info("dtoList");
		log.info(dtoList);
		
		dtoList.stream().forEach(board -> log.info(board));
	}
	
	@Test
	public void testSearch() {
		int page = 2;
		int count = 10;
		int skip = (page - 1) * count;
		
		String[] types = {"T", "C", "W"};
		String keyword = "Test";
		
		boardMapper.listSearch(skip, count, types, keyword);
	}

	
}
