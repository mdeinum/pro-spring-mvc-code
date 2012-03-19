package com.apress.prospringmvc.bookstore.web.controller;

import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UploadOrderController {

    private final Logger logger = LoggerFactory.getLogger(UploadOrderController.class);

    @RequestMapping(value = "/order/upload", method = RequestMethod.POST)
    public String handleUpload(final Part order) {
        logFile(order.getName(), order.getSize());
        return "redirect:/customer/account";
    }

    private void logFile(String name, long size) {
        this.logger.info("Received order: {}, size {}", name, size);
    }

}
