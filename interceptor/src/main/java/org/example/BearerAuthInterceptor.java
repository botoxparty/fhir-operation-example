package org.example;

import ca.uhn.fhir.rest.server.interceptor.auth.AuthorizationInterceptor;
import ca.uhn.fhir.rest.server.interceptor.auth.IAuthRule;
import ca.uhn.fhir.rest.server.interceptor.auth.RuleBuilder;
import ca.uhn.fhir.i18n.Msg;
import java.util.List;
import java.util.Map;

import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.AuthenticationException;

@SuppressWarnings("ConstantConditions")
public class BearerAuthInterceptor extends AuthorizationInterceptor {

    @Override
    public List<IAuthRule> buildRuleList(RequestDetails theRequestDetails) {
        Map<String, String> env = System.getenv();
        final String BEARER_TOKEN = env.get("TOKEN");

        boolean userIsAdmin = false;
        String authHeader = theRequestDetails.getHeader("Authorization");

        if (BEARER_TOKEN == null) {
            userIsAdmin = true;
        } else if (String.format("Bearer %s", BEARER_TOKEN).equals(authHeader)) {
            // This user has access to everything
            userIsAdmin = true;
        } else {
            // Throw an HTTP 401
            throw new AuthenticationException(Msg.code(644) + "Missing or invalid Authorization header value");
        }

        // If the user is an admin, allow everything
        if (userIsAdmin) {
            return new RuleBuilder()
                    .allowAll()
                    .build();
        }

        // By default, deny everything.
        return new RuleBuilder()
                .denyAll()
                .build();
    }
}
