package com.sailpoint.prototype.config.project;

import com.sailpoint.prototype.config.component.ComponentConfiguration;
import com.sailpoint.prototype.config.db.SailPointDataBaseConfiguration;
import com.sailpoint.prototype.config.vaadin.VaadinComponentConfiguration;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * Main project configuration
 */
@Configuration
@Import({
        SailPointDataBaseConfiguration.class,
        ComponentConfiguration.class,
        VaadinComponentConfiguration.class
})
public class SailPointConfiguration {

    /**
     * Create yaml properties placeholder for yaml
     * It is necessary to load YAML
     *
     * @return yaml properties place holder bean
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }
}
