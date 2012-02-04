package com.apress.prospringmvc.bookstore.web.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.document.AbstractJExcelView;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.apress.prospringmvc.bookstore.web.view.OrderExcelView;
import com.apress.prospringmvc.bookstore.web.view.OrderPdfView;
import com.apress.prospringmvc.bookstore.web.view.SimpleConfigurableViewResolver;

@Configuration
public class ViewConfiguration {

    @Bean
    public TilesConfigurer tilesConfigurer() {
        return new TilesConfigurer();
    }

    @Bean
    public TilesViewResolver tilesViewResolver() {
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        tilesViewResolver.setOrder(2);
        return tilesViewResolver;
    }

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        List<View> defaultViews = new ArrayList<View>();
        defaultViews.add(jsonOrderView());
        defaultViews.add(xmlOrderView());
        viewResolver.setDefaultViews(defaultViews);
        return viewResolver;
    }

    @Bean
    public MappingJacksonJsonView jsonOrderView() {
        MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
        jsonView.setModelKey("order");
        return jsonView;
    }

    @Bean
    public MarshallingView xmlOrderView() {
        MarshallingView xmlView = new MarshallingView(marshaller());
        xmlView.setModelKey("order");
        return xmlView;
    }

    @Bean
    public Marshaller marshaller() {
        return new XStreamMarshaller();
    }

    @Bean
    public ViewResolver pdfViewResolver() {
        SimpleConfigurableViewResolver viewResolver = new SimpleConfigurableViewResolver();
        Map<String, AbstractPdfView> views = new HashMap<String, AbstractPdfView>();
        views.put("order", new OrderPdfView());
        viewResolver.setViews(views);
        return viewResolver;
    }

    @Bean
    public ViewResolver xlsViewResolver() {
        SimpleConfigurableViewResolver viewResolver = new SimpleConfigurableViewResolver();
        Map<String, AbstractJExcelView> views = new HashMap<String, AbstractJExcelView>();
        views.put("order", new OrderExcelView());
        viewResolver.setViews(views);
        return viewResolver;
    }

}
