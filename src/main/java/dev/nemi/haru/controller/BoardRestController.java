package dev.nemi.haru.controller;

import dev.nemi.haru.service.board.BoardEditDTO;
import dev.nemi.haru.service.board.BoardService;
import dev.nemi.haru.service.board.BoardViewDTO;
import dev.nemi.haru.service.board.BoardWriteDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/board")
public class BoardRestController {

  private final BoardService boardService;

  public ResponseEntity<String> useOK() {
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(
      new JSONObject().put("success", true).toString()
    );
  }

  public ResponseEntity<String> useBR() {
    return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(
      new JSONObject().put("success", false).put("message", "Something went wrong, but it's your fault anyway.").toString()
    );
  }

  @GetMapping(value = "/get")
  public ResponseEntity<String> get() {
    List<BoardViewDTO> boardList = boardService.getBoardListAt(1L, 10);
    ResponseEntity<String> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(
      new JSONObject().put("success", true).put("data", new JSONArray(boardList)).toString()
    );
    log.info(response);
    return response;
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<String> get(@PathVariable("id") int id) {
    BoardViewDTO board = boardService.getBoard(id);
    ResponseEntity<String> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(
      new JSONObject().put("success", true).put("data", board).toString()
    );
    log.info(response);
    return response;
  }

  @PostMapping("/write")
  public ResponseEntity<String> write(BoardWriteDTO board) {
    log.info(board);
    int result = boardService.write(board);
    if (result > 0) return useOK();
    return useBR();
  }

  @PostMapping("/edit")
  public ResponseEntity<String> edit(BoardEditDTO board) {
    log.info(board);
    int result = boardService.edit(board);
    if (result > 0) return useOK();
    return useBR();
  }

}
