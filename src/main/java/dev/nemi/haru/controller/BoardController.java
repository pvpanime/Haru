package dev.nemi.haru.controller;

import dev.nemi.haru.service.board.BoardEditDTO;
import dev.nemi.haru.service.board.BoardService;
import dev.nemi.haru.service.board.BoardViewDTO;
import dev.nemi.haru.service.board.BoardWriteDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  private @NotNull String useIndex(@NotNull Model model, long pg) {
    int numPerPage = 50;
    List<BoardViewDTO> list = boardService.getBoardListAt(pg, numPerPage);
    long maxPage = boardService.getMaxPages(numPerPage);

    model.addAttribute("list", list);
    model.addAttribute("currentPage", pg);
    model.addAttribute("maxPage", maxPage);

    return "board/index";
  }

  @GetMapping("/board")
  public String index(Model model) {
    return useIndex(model, 1L);  // Default to page 1
  }

  @GetMapping("/board/page/{pg}")
  public String index(Model model, @PathVariable long pg) {
    return useIndex(model, pg);  // Use the provided page number
  }

  @GetMapping("/board/read/{id}")
  public String read(Model model, @PathVariable long id) {
    BoardViewDTO board = boardService.getBoard(id);
    model.addAttribute("board", board);
    return "board/read";
  }

  @GetMapping("/board/write")
  public String write() {
    log.info("board/write");
    return "board/write";
  }

  @PostMapping("/board/write")
  public String write(BoardWriteDTO board) {
    int result = boardService.write(board);
    if (result > 0) return "redirect:/board";
    else return "forward:/error";
  }

  @PostMapping("/board/edit")
  public String edit(BoardEditDTO board) {
    int result = boardService.edit(board);
    if (result > 0) return "redirect:/board";
    else return "forward:/error";
  }

}
