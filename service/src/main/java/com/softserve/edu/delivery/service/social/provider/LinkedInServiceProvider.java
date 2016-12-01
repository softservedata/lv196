package com.softserve.edu.delivery.service.social.provider;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("linkedInServiceProvider")
public class LinkedInServiceProvider implements SocialServiceProvider{

    @Value("${linkedin.apiKey}")
    private String apiKey;

    @Value("${linkedin.apiSecret}")
    private String apiSecret;

    @Value("${linkedin.callbackUrl}")
    private String callbackUrl;

    @Value("${linkedin.scope}")
    private String scope;

    private DefaultApi10a provider = new LinkedInApi();


    @Override
    public OAuthService getService() {
        return new ServiceBuilder()
                .apiKey(this.apiKey)
                .apiSecret(this.apiSecret)
                .callback(this.callbackUrl)
                .provider(this.provider)
                .build();
    }
}