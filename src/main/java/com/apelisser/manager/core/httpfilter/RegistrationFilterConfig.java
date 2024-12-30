package com.apelisser.manager.core.httpfilter;

import com.apelisser.manager.core.context.Context;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class RegistrationFilterConfig {

    private final Context context;

    public RegistrationFilterConfig(Context context) {
        this.context = context;
    }

    @Bean
    public FilterRegistrationBean<ContextFilter> requestIdContextFilter() {
        FilterRegistrationBean<ContextFilter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new ContextFilter(context));
        filterBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterBean;
    }

}
