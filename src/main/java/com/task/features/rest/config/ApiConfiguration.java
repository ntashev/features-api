package com.task.features.rest.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author nikolay.tashev on 22/01/2018.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiConfiguration implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {

        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();

        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.setInitParameter("contextConfigLocation", "com.task.features");
    }
}
