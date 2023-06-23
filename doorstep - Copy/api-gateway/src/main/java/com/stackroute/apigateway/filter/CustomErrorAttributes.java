package com.stackroute.apigateway.filter;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE));
        errorAttributes.remove("timestamp");
        errorAttributes.remove("path");
        errorAttributes.remove("requestId");
        errorAttributes.remove("version");
        return errorAttributes;
    }
}