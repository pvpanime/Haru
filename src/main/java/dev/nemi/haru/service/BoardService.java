package dev.nemi.haru.service;

import java.util.List;

public interface BoardService {
  BoardViewDTO getBoard(long id);
  List<BoardViewDTO> getBoardListAt(long start, int count);
}
