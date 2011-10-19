package com.apress.prospringmvc.pizzarus.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PizzasController {

  
    @ExceptionHandler()
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Exception while handling request.")
    public void handleException() {
      
    }
}
