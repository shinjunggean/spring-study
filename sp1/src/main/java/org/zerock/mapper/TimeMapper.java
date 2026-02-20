package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

// SQL을 실행시키기 위한 메소드를 정의하는 DAO 역할
public interface TimeMapper {
	// DB의 현재 시간을 조회하는 SQL
	@Select("SELECT NOW()")
	String getTime();
	
	String getTime2();
}
