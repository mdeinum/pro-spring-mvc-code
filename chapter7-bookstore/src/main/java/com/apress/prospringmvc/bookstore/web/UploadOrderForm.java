package com.apress.prospringmvc.bookstore.web;

import org.springframework.web.multipart.MultipartFile;

/**
 * Form for handling file uploads.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
public class UploadOrderForm {

    private MultipartFile order;

    public MultipartFile getOrder() {
        return this.order;
    }

    public void setOrder(MultipartFile order) {
        this.order = order;
    }

}
