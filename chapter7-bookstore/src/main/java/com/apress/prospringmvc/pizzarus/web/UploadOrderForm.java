package com.apress.prospringmvc.pizzarus.web;

import org.springframework.web.multipart.MultipartFile;

public class UploadOrderForm {

    private MultipartFile order;

    public MultipartFile getOrder() {
        return this.order;
    }

    public void setOrder(final MultipartFile order) {
        this.order = order;
    }

}
