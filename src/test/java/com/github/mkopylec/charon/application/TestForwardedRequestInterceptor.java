package com.github.mkopylec.charon.application;

import com.github.mkopylec.charon.core.http.ForwardedRequestInterceptor;
import com.github.mkopylec.charon.core.http.RequestData;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpMethod.DELETE;

@Component
@ConditionalOnProperty("test.forwarded-request-interceptor-enabled")
public class TestForwardedRequestInterceptor implements ForwardedRequestInterceptor {

    public static final String INTERCEPTED_AUTHORIZATION = "intercepted-authorization";

    @Override
    public void intercept(RequestData data) {
        data.setMethod(DELETE);
        data.getHeaders().set(AUTHORIZATION, INTERCEPTED_AUTHORIZATION);
    }
}
