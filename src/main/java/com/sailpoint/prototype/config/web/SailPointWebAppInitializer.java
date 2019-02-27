package com.sailpoint.prototype.config.web;

import com.sailpoint.prototype.config.project.SailPointConfiguration;
import com.vaadin.flow.spring.SpringServlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Common servlet sail point initializer
 */
@Slf4j
public class SailPointWebAppInitializer implements WebApplicationInitializer {

    /**
     * Init all spring servlet stuff
     *
     * @param servletContext - current servlet context
     */
    public void onStartup(ServletContext servletContext) {
        log.info("Initialize spring context with:[{}] configuration", SailPointConfiguration.class.getName());
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.setConfigLocation(SailPointConfiguration.class.getName());

        log.info("Adding listener to servlet context");
        servletContext.addListener(new ContextLoaderListener(springContext));

        log.info("Adding dispatcher to servlet context");
        ServletRegistration.Dynamic dispatcher = servletContext
                .addServlet("dispatcher", new SpringServlet(springContext, true));

        log.info("Set loadingOnStartup to 1 for spring servlet");
        dispatcher.setLoadOnStartup(1);
        log.info("Add mapping for /* for spring servlet");
        dispatcher.addMapping("/");
    }
}
