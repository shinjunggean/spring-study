package org.zerock.mapper;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")	// 테스트에 필요한 설정을 로딩하기 위해 설정
@Log4j2
public class TimeMapperTests {
	
	@Autowired	
	private TimeMapper mapper;
	
	@Test
	public void testTime1() {
		log.info("--------");
		log.info(mapper.getTime());
	}
	@Test
	public void testTime2() {
		log.info("========");
		log.info(mapper.getTime2());
	}
}
