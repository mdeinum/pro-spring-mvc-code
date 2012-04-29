package com.apress.prospringmvc.bookstore.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apress.prospringmvc.bookstore.web.UploadOrderForm;

/**
 * Controller to handle file uploads.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
@Controller
public class UploadOrderController {

    private final Logger logger = LoggerFactory.getLogger(UploadOrderController.class);

    @RequestMapping(value = "/order/upload", method = RequestMethod.POST)
    public String handleUpload(UploadOrderForm form) {
        logFile(form.getOrder().getName(), form.getOrder().getSize());
        return "redirect:/customer/account";
    }

    private void logFile(String name, long size) {
        this.logger.info("Received order: {}, size {}", name, size);
    }

}
