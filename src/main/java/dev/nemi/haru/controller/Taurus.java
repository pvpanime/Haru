package dev.nemi.haru.controller;

import dev.nemi.haru.service.tasker.Tasker404;
import dev.nemi.haru.service.tasker.TaskerBadRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@Log4j2
@ControllerAdvice
public class Taurus {

  public ResponseEntity<String> a(int status, String mime) {
    return ResponseEntity.status(status).contentType(MediaType.parseMediaType(mime)).body("Bad Request");
  }

  @ExceptionHandler(value = { TaskerBadRequest.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String badRequest() {
    return "view400";
  }


  @ExceptionHandler(value = { NoHandlerFoundException.class, Tasker404.class })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String notFound() {
    return "view404";
  }


  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
    String paramName = ex.getName();
    String paramValue = ex.getValue() != null ? ex.getValue().toString() : "null";
    String errorMessage = String.format("Invalid value '%s' for parameter '%s'. Expected a number.", paramValue, paramName);

    return "view404";
//    return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.valueOf("text/plain")).body(errorMessage);
//    return errorMessage; // You can customize this to return an error view or JSON response
  }

  @ResponseBody
  @ExceptionHandler(NumberFormatException.class)
  public String numberFormatAdvice(NumberFormatException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public String anyException(Exception ex) {
    Arrays.stream(ex.getStackTrace()).forEach(log::error);
    return ex.getMessage();
  }
}
