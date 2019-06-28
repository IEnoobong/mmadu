package com.mmadu.tokenservice.controllers;

import com.mmadu.tokenservice.entities.AppToken;
import com.mmadu.tokenservice.entities.DomainConfiguration;
import com.mmadu.tokenservice.models.CheckTokenRequest;
import com.mmadu.tokenservice.models.CheckTokenResult;
import com.mmadu.tokenservice.models.DomainAuthTokenModel;
import com.mmadu.tokenservice.services.AppTokenService;
import com.mmadu.tokenservice.services.DomainConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private AppTokenService appTokenService;
    @Autowired
    private DomainConfigurationService domainConfigurationService;

    @GetMapping("/generate")
    public AppToken generateToken() {
        return appTokenService.generateToken();
    }

    @GetMapping("/retrieve/{tokenId}")
    public AppToken getToken(@PathVariable("tokenId") String tokenId) {
        return appTokenService.getToken(tokenId);
    }

    @GetMapping("/reset/{tokenId}")
    public AppToken resetToken(@PathVariable("tokenId") String tokenId) {
        return appTokenService.resetToken(tokenId);
    }

    @PostMapping("/checkDomainToken")
    public CheckTokenResult checkToken(@RequestBody CheckTokenRequest request) {
        boolean matches = domainConfigurationService.tokenMatchesDomain(request.getToken(), request.getDomainId());
        CheckTokenResult result = new CheckTokenResult();
        result.setMatches(matches);
        return result;
    }

    @PostMapping("/setDomainAuthToken")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setDomainAuthToken(@RequestBody DomainAuthTokenModel request) {
        domainConfigurationService.setAuthTokenForDomain(request.getTokenId(), request.getDomainId());
    }

    @GetMapping("/domainAuth/{domainId}")
    public DomainAuthTokenModel getDomainAuthToken(@PathVariable("domainId") String domainId) {
        DomainConfiguration configuration = domainConfigurationService.getConfigurationForDomain(domainId);
        return configurationToModel(configuration);
    }

    private DomainAuthTokenModel configurationToModel(DomainConfiguration configuration) {
        DomainAuthTokenModel model = new DomainAuthTokenModel();
        model.setTokenId(configuration.getAuthenticationApiToken());
        model.setDomainId(configuration.getDomainId());
        return model;
    }
}
