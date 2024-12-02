package dev.nemi.haru.mapper;

import dev.nemi.haru.board.BoardVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring.xml")
public class HaruMapperTest {

  @Autowired(required = false)
  private TimeMapper timeMapper;

  @Autowired(required = false)
  private CronalMapper cronalMapper;

  @Autowired(required = false)
  private BoardMapper boardMapper;

  @Test
  public void testGetTime() {
    Instant instant = timeMapper.getTime();
    log.info(instant);
  }

  @Test
  public void testMyBatis() {
    Instant instant = cronalMapper.getNow();
    log.info(instant);
  }

  @Test
  public void boardTest() {
    BoardVO board = boardMapper.getBoard(1);
    log.info(board);

    List<BoardVO> boardList = boardMapper.getBoardListAt(1, 10);
    boardList.forEach(log::info);
  }

}
