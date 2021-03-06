package com.mmadu.identity.models.authorization;

import com.mmadu.identity.entities.DomainIdentityConfiguration;
import com.mmadu.identity.models.authorization.errors.AuthorizationError;
import com.mmadu.identity.models.client.MmaduClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AuthorizationContext {
    private Map<String, Object> context = new HashMap<>();
    private AuthorizationResult result = new AuthorizationResult();
    private Object authorizer;
    private MmaduClient client;
    private DomainIdentityConfiguration domainConfiguration;

    public <T> Optional<T> get(String key) {
        return Optional.ofNullable(context.get(key))
                .map(t -> (T) t);
    }

    public void set(String key, Object value) {
        context.put(key, value);
    }

    public void fail(AuthorizationError error) {
        result.setComplete(true);
        error.setState(result.getState());
        result.setError(error);
    }

    public void setRedirectUri(String redirectUri) {
        this.result.setRedirectUri(redirectUri);
        this.result.setRedirectUriSpecified(true);
    }

    public void setRedirectUri(String redirectUri, boolean specified) {
        this.result.setRedirectUri(redirectUri);
        this.result.setRedirectUriSpecified(specified);
    }

    public void succeed(String redirectUri, RedirectData data) {
        this.result.setComplete(true);
        this.result.setRedirectUri(redirectUri);
        this.result.setData(data);
    }

    public void succeed(RedirectData data) {
        this.result.setComplete(true);
        this.result.setData(data);
    }

    public AuthorizationResult getResult() {
        return result;
    }

    public void setState(String state){
        result.setState(state);
    }

    public void setAuthorizer(Object authorizer) {
        this.authorizer = authorizer;
    }

    public Object getAuthorizer() {
        return authorizer;
    }

    public void setClient(MmaduClient client) {
        this.client = client;
    }

    public MmaduClient getClient() {
        return client;
    }

    public DomainIdentityConfiguration getDomainConfiguration() {
        return domainConfiguration;
    }

    public void setDomainConfiguration(DomainIdentityConfiguration domainConfiguration) {
        this.domainConfiguration = domainConfiguration;
    }
}
