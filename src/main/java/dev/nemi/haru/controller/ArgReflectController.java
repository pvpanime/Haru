package dev.nemi.haru.controller;

import dev.nemi.haru.service.BoardWriteDTO;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/reflect")
public class ArgReflectController {

  @GetMapping("/json")
  public ResponseEntity<String> json(@RequestParam Map<String, String> params) {
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new JSONObject(params).toString());
  }

  @GetMapping("/instant")
  public ResponseEntity<String> instant(Instant at) {
    log.info("{} {}", at.getClass().getName(), at);
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(
      new JSONObject().put("at", at).toString()
    );
  }

  @GetMapping("/local")
  public ResponseEntity<String> local(LocalDateTime at) {
    log.info("{} {}", at.getClass().getName(), at);
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(
      new JSONObject().put("at", at).toString()
    );
  }

  @GetMapping("/fixed")
  public ResponseEntity<String> fixed(BoardWriteDTO dto) {
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new JSONObject(dto).toString());
  }
}
