package com.SoftQual.SQ.Controllers;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;

@SpringBootApplication
@RestController
public class IndexController {

  @SuppressWarnings("null")
  @GetMapping(value = { "/", "/index" })
  public ResponseEntity<Resource> index() throws IOException {
    Resource resource = new ClassPathResource("static/index.html");
    return ResponseEntity.ok()
        .contentType(MediaType.TEXT_HTML)
        .body(resource);
  }

  @GetMapping("/calculate")
  public double calculate(
      @RequestParam double num1,
      @RequestParam double num2,
      @RequestParam String operation,
      @RequestParam(required = false) Double percentage) {
    switch (operation) {
      case "add":
        return num1 + num2;
      case "subtract":
        return num1 - num2;
      case "multiply":
        return num1 * num2;
      case "divide":
        if (num2 == 0) {
          throw new IllegalArgumentException("Cannot divid by zero");
        }
        return num1 / num2;
      default:
        throw new IllegalArgumentException("Invalid Operation!");
    }
  }
}
