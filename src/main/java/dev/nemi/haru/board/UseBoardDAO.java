package dev.nemi.haru.board;

import java.sql.SQLException;
import java.util.List;

public interface UseBoardDAO {
  BoardVO getById(long id) throws SQLException;
  List<BoardVO> getListAt(long start, long offset) throws SQLException;
  void update(BoardVO board) throws SQLException;
  void delete(long id) throws SQLException;
}
