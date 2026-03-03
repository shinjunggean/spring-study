package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.BoardDTO;

public interface BoardMapper {

	int insert(BoardDTO dto);
	
	BoardDTO selectOne(Long bno);
	
	int remove(Long bno);
	
	int update(BoardDTO dto);
	
	List<BoardDTO> list();
	
	
	// MyBatis에서 여러 값을 전달하려면 @Param,Map 타입, 또는 객체 타입을 이용
	List<BoardDTO> list2(@Param("skip") int skip, @Param("count") int count);
	//	매퍼 XML에서		
	//	LIMIT #{skip{m #{count{ --MyBatis는 skip, count라는 이름을 모름

	int listCount();
	
	List<BoardDTO> listSearch(
			@Param("skip") int skip,
			@Param("count") int count,
			@Param("types") String[] types,
			@Param("keyword") String keyword);

	int listCountSearch(@Param("types") String[] types, 
			@Param("keyword") String keyword);
	
	
	
}