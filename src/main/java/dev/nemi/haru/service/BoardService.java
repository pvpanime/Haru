package dev.nemi.haru.service;

import java.util.List;

public interface BoardService {
  long getMaxPages(int numPerPage);
  BoardViewDTO getBoard(long id);
  List<BoardViewDTO> getBoardListAt(long start, int count);
  int write(BoardWriteDTO dto);
  int edit(BoardEditDTO dto);
}
