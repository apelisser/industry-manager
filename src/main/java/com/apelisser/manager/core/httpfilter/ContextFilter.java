package com.apelisser.manager.core.httpfilter;

import com.apelisser.manager.core.context.Context;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

import static com.apelisser.manager.core.context.ContextKey.REQUEST_ID;

public class ContextFilter implements Filter {

    private static final String REQUEST_ID_HEADER_NAME = "x-request-id";

    private final Context context;

    public ContextFilter(Context context) {
        this.context = context;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            this.addRequestIdInContext();
            this.addRequestIdInResponseHeader(response);
            chain.doFilter(request, response);
        } finally {
            context.clear();
        }
    }

    private void addRequestIdInContext() {
        String uuid = UUID.randomUUID().toString();
        context.add(REQUEST_ID, uuid);
    }

    private void addRequestIdInResponseHeader(ServletResponse response) {
        context.get(REQUEST_ID).ifPresent(requestId -> {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader(REQUEST_ID_HEADER_NAME, requestId);
        });
    }

}
