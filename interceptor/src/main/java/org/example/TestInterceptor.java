package org.example;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.AuthenticationException;

import jakarta.servlet.http.HttpServletResponse;

@Interceptor
public class TestInterceptor {

    @Hook(Pointcut.SERVER_INCOMING_REQUEST_POST_PROCESSED)
    public void incomingRequestPostProcessed(RequestDetails theRequest, HttpServletResponse theResponse) {
        // Your interceptor logic here
        System.out.println("Incoming request post processed");
    }
}
