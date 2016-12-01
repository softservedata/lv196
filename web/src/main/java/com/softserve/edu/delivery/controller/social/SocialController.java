package com.softserve.edu.delivery.controller.social;

import com.softserve.edu.delivery.service.social.SocialUserService;
import com.softserve.edu.delivery.service.social.dto.SocialUserDTO;
import com.softserve.edu.delivery.service.social.provider.SocialServiceProvider;
import com.softserve.edu.delivery.service.social.provider.api.util.ResponseParser;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;
import static com.softserve.edu.delivery.config.SecurityConstraints.*;

@Controller
public class SocialController {

    private static final String SOCIAL_REQUEST_TOKEN = "oauthRequestToken";

    private final Logger logger = LoggerFactory.getLogger(SocialController.class);

    @Autowired
    private SocialServiceProvider linkedInServiceProvider;

    @Autowired
    private SocialServiceProvider googleServiceProvider;

    @Autowired
    private SocialUserService socialUserService;


    @GetMapping(value = "/login/linkedin")
    public ModelAndView linkedInSignIn(WebRequest webRequest) {
        Token requestToken;
        OAuthService oAuthService = this.linkedInServiceProvider.getService();
        requestToken = oAuthService.getRequestToken();
        webRequest.setAttribute(SOCIAL_REQUEST_TOKEN, requestToken, SCOPE_SESSION);

        logger.info("Return to linkedIn authorization url.");

        return new ModelAndView("redirect:" + oAuthService.getAuthorizationUrl(requestToken));
    }

    private static final String LINKEDIN_RESOURCE = "https://api.linkedin.com/v1/people/~:(id,email-address,firstName,lastName,picture-url)?format=json";

    @GetMapping(value = "/login/linkedin/callback")
    public ModelAndView linkedInCallback(@RequestParam(value = "oauth_verifier") String oauthVerifier, WebRequest webRequest) {
        OAuthService oAuthService = this.linkedInServiceProvider.getService();
        Token requestToken = (Token) webRequest.getAttribute(SOCIAL_REQUEST_TOKEN, SCOPE_SESSION);

        Verifier verifier = new Verifier(oauthVerifier);
        Token accessToken = oAuthService.getAccessToken(requestToken, verifier);

        OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, LINKEDIN_RESOURCE);
        oAuthService.signRequest(accessToken, oAuthRequest);

        logger.info("Sending application request to linkedIn.");

        Response oAuthResponse = oAuthRequest.send();
        String responseBody = oAuthResponse.getBody();

        logger.info("Got response body from linked in. Response body: " + oAuthResponse.getBody());

        SocialUserDTO socialUser = ResponseParser.fromLinkedIn(responseBody);
        this.socialUserService.signIn(socialUser);

        return new ModelAndView("redirect:" + AUTH_REDIRECT_URL);
    }

    @GetMapping(value = "/login/google")
    public ModelAndView googleSignIn() {
        OAuthService oAuthService = this.googleServiceProvider.getService();
        String authorizationUrl = oAuthService.getAuthorizationUrl(OAuthConstants.EMPTY_TOKEN);

        logger.info("Return to google authorization url.");
        return new ModelAndView("redirect:" + authorizationUrl);
    }

    private static final String GOOGLE_RESOURCE = "https://www.googleapis.com/oauth2/v2/userinfo?alt=json";

    @GetMapping(value = "/login/google/callback")
    public ModelAndView googleCallback(@RequestParam(value = "code", required = false) String verifierCode) {
        Verifier verifier = new Verifier(verifierCode);
        OAuthService service = this.googleServiceProvider.getService();
        Token accessToken = service.getAccessToken(OAuthConstants.EMPTY_TOKEN, verifier);
        OAuthRequest request = new OAuthRequest(Verb.GET, GOOGLE_RESOURCE);
        service.signRequest(accessToken, request);
        Response response = request.send();
        String responseBody = response.getBody();
        logger.info("Google response body: " + responseBody);

        SocialUserDTO socialUser = ResponseParser.fromGoogle(responseBody);
        this.socialUserService.signIn(socialUser);

        return new ModelAndView("redirect:/authRedirect");
    }
}