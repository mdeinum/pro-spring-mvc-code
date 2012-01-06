package com.apress.prospringmvc.pizzarus.web;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.MultipartResolver;

/**
 * Controller to show the handling of uploads. Uncomment one of the POST methods to handle uploads in a different way.
 * @author marten
 *
 */
@Controller
@RequestMapping(value = "/order-upload.htm")
public class UploadOrderController {

    private final Logger logger = LoggerFactory.getLogger(UploadOrderController.class);

    @RequestMapping(method = RequestMethod.GET)
    public void index() {
    }

    /**
     * Sample method using {@link javax.servlet.http.Part} to represent the file. This method only works in a Servlet 3.0 environment.
     * @param order the uploaded order
     * @return redirect view
     */
    //    @RequestMapping(method = RequestMethod.POST)
    public String handleUpload(final Part order) {
        logFile(order.getName(), order.getSize());
        return "redirect:order-success.htm";
    }

    /**
     * Sample method using {@link org.springframework.web.multipart.MultipartFile} to represent the file. This method works in different servlet environments.
     * @param order the uploaded order
     * @return redirect view
     */
    //    @RequestMapping(method = RequestMethod.POST)
    public String handleUpload(final MultipartFile order) {
        logFile(order.getOriginalFilename(), order.getSize());
        return "redirect:order-success.htm";
    }

    /**
     * Sample method using {@link org.springframework.web.multipart.MultipartHttpServletRequest} to gain access to the request. 
     * This method is portable to other servlet containers and relies on which {@link MultipartResolver} is configured.
     * @param request
     * @return
     */
    //    @RequestMapping(method = RequestMethod.POST)
    public String handleUpload(final MultipartHttpServletRequest request) {
        Map<String, MultipartFile> files = request.getFileMap();
        for (Entry<String, MultipartFile> file : files.entrySet()) {
            MultipartFile order = file.getValue();
            logFile(order.getOriginalFilename(), order.getSize());
        }
        return "redirect:order-success.htm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleUpload(final MultipartRequest request) {
        Map<String, MultipartFile> files = request.getFileMap();
        for (Entry<String, MultipartFile> file : files.entrySet()) {
            MultipartFile order = file.getValue();
            logFile(order.getOriginalFilename(), order.getSize());
        }
        return "redirect:order-success.htm";
    }

    private void logFile(final String name, final long size) {
        this.logger.info("Received order: {}, size {}", name, size);
    }

}
