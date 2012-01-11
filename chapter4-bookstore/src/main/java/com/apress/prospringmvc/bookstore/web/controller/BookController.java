package com.apress.prospringmvc.bookstore.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/book")
public class BookController {

    @RequestMapping(value = "${bookId}/image", method = RequestMethod.GET)
    public void image(@PathVariable long bookId, HttpServletResponse response) {
        //todo write image as byte[] back to the user

    }

}
