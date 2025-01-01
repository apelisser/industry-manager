package com.apelisser.manager.core.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ResourceMessageConfig {

    private final I18NProperties i18NProperties;

    public ResourceMessageConfig(I18NProperties i18NProperties) {
        this.i18NProperties = i18NProperties;
    }

    /**
     * Creates and configures a ResourceBundleMessageSource bean for message retrieval.
     *
     * @return a ResourceBundleMessageSource bean configured with the specified settings
     */
    @Bean
    MessageSource messageSource() {
        var source = new ResourceBundleMessageSource();
        source.setBasenames(i18NProperties.getBaseNames());
        source.setDefaultEncoding(i18NProperties.getDefaultEncoding().displayName());
        source.setFallbackToSystemLocale(i18NProperties.isFallbackToSystemLocale());
        source.setDefaultLocale(i18NProperties.getDefaultLocale());
        source.setUseCodeAsDefaultMessage(i18NProperties.isUseCodeAsDefaultMessage());

        return source;
    }

}
