package com.softserve.edu.delivery.service.social.provider;

import com.softserve.edu.delivery.service.social.provider.api.Google2Api;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.DefaultApi20;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("googleServiceProvider")
public class GoogleServiceProvider implements SocialServiceProvider {

    @Value("${google.apiKey}")
    private String apiKey;

    @Value("${google.apiSecret}")
    private String apiSecret;

    @Value("${google.callbackUrl}")
    private String callbackUrl;

    @Value("${google.scope}")
    private String scope;

    private DefaultApi20 provider = new Google2Api();


    @Override
    public OAuthService getService() {
        return new ServiceBuilder()
                .apiKey(this.apiKey)
                .apiSecret(this.apiSecret)
                .callback(this.callbackUrl)
                .scope(this.scope)
                .provider(this.provider)
                .build();
    }

    @Override
    public String getScope() {
        return this.scope;
    }
}
