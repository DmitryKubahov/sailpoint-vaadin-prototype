package com.sailpoint.prototype.config.vaadin;

import com.sailpoint.prototype.vaadin.XmlComponentVaadinProviderImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for vaadin components
 */
@Configuration
@ComponentScan(basePackageClasses = {
        XmlComponentVaadinProviderImpl.class
})
public class VaadinComponentConfiguration {

}
