package dev.nemi.haru.service.board;

import dev.nemi.haru.BoardVO;
import dev.nemi.haru.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceReal implements BoardService {

  private final BoardMapper boardMapper;
  private final ModelMapper modelMapper;

  @Override
  public long getMaxPages(int numPerPage) {
    return boardMapper.getMaxPages(numPerPage);
  }

  @Override
  public BoardViewDTO getBoard(long id) {
    return modelMapper.map(boardMapper.getBoard(id), BoardViewDTO.class);
  }

  @Override
  public List<BoardViewDTO> getBoardListAt(long start, int count) {
    return boardMapper.getBoardListAt(start, count).stream().map(board -> modelMapper.map(board, BoardViewDTO.class)).collect(Collectors.toList());
  }

  @Override
  public int write(BoardWriteDTO dto) {
    return boardMapper.insert(modelMapper.map(dto, BoardVO.class));
  }

  @Override
  public int edit(BoardEditDTO dto) {
    return boardMapper.update(modelMapper.map(dto, BoardVO.class));
  }
}
