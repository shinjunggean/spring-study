package org.zerock.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.ReplyDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")	// 테스트에 필요한 설정을 로딩하기 위해 설정
@Log4j2
public class ReplyMapperTests {

	@Autowired
	private ReplyMapper replyMapper;
	
	@Test
	public void testInsert() {
		Long bno = 253L;
		
		// 새로운 댓글 생성
		ReplyDTO replyDTO = ReplyDTO.builder()
				.bno(bno)
				.replyText("Replay......")
				.replyer("user1")
				.build();
		
		replyMapper.insert(replyDTO);
	}
	
	@Test
	public void testRead() {
		Long rno = 1L;
		
		log.info("-----------------------------");
		log.info(replyMapper.read(rno));
	}
	
	@Test
	public void testDelete() {
		Long rno = 1L;
		
		log.info("-----------------------------");
		log.info(replyMapper.delete(rno));
	}
	
	@Test
	public void testUpdate() {
		ReplyDTO replyDTO = ReplyDTO.builder()
				.rno(1L)
				.replyText("Update Text")
				.build();
		
		log.info("-----------------------------");
		log.info(replyMapper.update(replyDTO));
	}
	
	@Test
	public void testInserts() {
		Long[] bnos = {216L, 217L, 218L};
		
		for(Long bno : bnos) {
			for(int i =0; i < 10; i++) {
				ReplyDTO replyDTO = ReplyDTO.builder()
						.bno(bno)
						.replyText("Sample Reply")
						.replyer("replayer1")
						.build();
				
				replyMapper.insert(replyDTO);
			}
		}
	}
	
	@Test
	public void testListOfBoard() {
		Long bno = 216L;
		List<ReplyDTO> replyList = replyMapper.listOfBoard(bno, 0, 10);
		
		replyList.forEach(reply -> log.info(reply));
	}

}
