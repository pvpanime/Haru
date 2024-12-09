package dev.nemi.haru.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class HaruController {

  @GetMapping(value = "/haru", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> useHaru() {
    log.info("haru");
//    return "This is the String body";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.TEXT_PLAIN);
    return new ResponseEntity<>("this is Haru", headers, HttpStatus.OK);
  }
}
