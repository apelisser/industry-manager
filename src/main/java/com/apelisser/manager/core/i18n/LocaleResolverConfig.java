package com.apelisser.manager.core.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class LocaleResolverConfig {

    private final I18NProperties i18NProperties;

    public LocaleResolverConfig(I18NProperties i18NProperties) {
        this.i18NProperties = i18NProperties;
    }

    /**
     * Configures a locale resolver that determines the current locale based on
     * the "Accept-Language" header in the incoming HTTP request.
     *
     * @return a configured locale resolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(i18NProperties.getDefaultLocale());
        return localeResolver;
    }

}
