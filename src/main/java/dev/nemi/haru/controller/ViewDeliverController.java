package dev.nemi.haru.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class ViewDeliverController {

  @GetMapping("/furk")
  public void furk() {
    log.info("furk");
  }
}
