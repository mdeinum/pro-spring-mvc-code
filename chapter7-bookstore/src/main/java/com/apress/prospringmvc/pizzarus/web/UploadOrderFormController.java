package com.apress.prospringmvc.pizzarus.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/order-upload-form.htm")
public class UploadOrderFormController {

    private final Logger logger = LoggerFactory.getLogger(UploadOrderFormController.class);

    @RequestMapping(method = RequestMethod.GET)
    @ModelAttribute("orderForm")
    public UploadOrderForm index() {
        return new UploadOrderForm();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleForm(final UploadOrderForm orderForm) {
        MultipartFile order = orderForm.getOrder();
        logFile(order.getOriginalFilename(), order.getSize());
        return "redirect:order-success.htm";

    }

    private void logFile(final String name, final long size) {
        this.logger.info("Received order: {}, size {}", name, size);
    }
}
