package com.github.emails.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
public class EmailConfig {

    private static final String PATH_TO_TEMPLATE = "classpath:/templates/";

    @Bean
    public FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean() {
        FreeMarkerConfigurationFactoryBean b = new FreeMarkerConfigurationFactoryBean();
        b.setPreferFileSystemAccess(Boolean.FALSE);
        b.setTemplateLoaderPaths(PATH_TO_TEMPLATE);
        return b;
    }

}
