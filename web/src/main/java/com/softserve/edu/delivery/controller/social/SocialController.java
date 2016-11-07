package com.softserve.edu.delivery.controller.social;

import com.softserve.edu.delivery.service.social.SocialUserService;
import com.softserve.edu.delivery.service.social.dto.SocialUserDTO;
import com.softserve.edu.delivery.service.social.provider.SocialServiceProvider;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

@Controller
public class SocialController {

    private static final String SOCIAL_REQUEST_TOKEN = "oauthRequestToken";
    private static final String SOCIAL_ACCESS_TOKEN = "oauthAccessToken";

    private final Logger logger = LoggerFactory.getLogger(SocialController.class);

    @Autowired
    private SocialServiceProvider linkedInServiceProvider;

    @Autowired
    private SocialServiceProvider googleServiceProvider;

    @Autowired
    private SocialUserService linkedInUserService;

    @Autowired
    private SocialUserService googleUserService;


    @RequestMapping(value = "/login/linkedin")
    public ModelAndView linkedInSignIn(WebRequest webRequest) {
        Token requestToken = (Token) webRequest.getAttribute(SOCIAL_REQUEST_TOKEN, SCOPE_SESSION);
        Token accessToken = (Token) webRequest.getAttribute(SOCIAL_ACCESS_TOKEN, SCOPE_SESSION);
        if (requestToken == null || accessToken == null) {
            OAuthService oAuthService = this.linkedInServiceProvider.getService();
            requestToken = oAuthService.getRequestToken();
            webRequest.setAttribute(SOCIAL_REQUEST_TOKEN, requestToken, SCOPE_SESSION);

            logger.info("Return to linkedIn authorization url.");

            return new ModelAndView("redirect:" + oAuthService.getAuthorizationUrl(requestToken));
        } else {
            return new ModelAndView("redirect:" + "/authRedirect");
        }
    }

    @RequestMapping(value = "/login/linkedin/callback")
    public ModelAndView linkedInCallback(@RequestParam(value="oauth_verifier", required=false) String oauthVerifier, WebRequest webRequest) {
        OAuthService oAuthService = this.linkedInServiceProvider.getService();
        Token requestToken = (Token) webRequest.getAttribute(SOCIAL_REQUEST_TOKEN, SCOPE_SESSION);

        Verifier verifier = new Verifier(oauthVerifier);
        Token accessToken = oAuthService.getAccessToken(requestToken, verifier);

        webRequest.setAttribute(SOCIAL_ACCESS_TOKEN, accessToken, SCOPE_SESSION);

        OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, this.linkedInServiceProvider.getScope());
        oAuthService.signRequest(accessToken, oAuthRequest);

        logger.info("Sending application request to linkedIn.");

        Response oAuthResponse = oAuthRequest.send();
        String responseBody = oAuthResponse.getBody();

        logger.info("ResponseBody:" + oAuthResponse.getBody());

        logger.info("Got response body from linked in");

        SocialUserDTO socialUser = this.linkedInUserService.parseResponse(responseBody);
        this.linkedInUserService.signIn(socialUser);

        return new ModelAndView("redirect:/authRedirect");
    }

    @RequestMapping(value = "/login/google")
    public ModelAndView googleSignIn() {
        OAuthService oAuthService = this.googleServiceProvider.getService();
        String authorizationUrl = oAuthService.getAuthorizationUrl(OAuthConstants.EMPTY_TOKEN);

        logger.info("Return to google authorization url.");

        return new ModelAndView("redirect:" + authorizationUrl);
    }

    private static final String GOOGLE_RESOURCE = "https://www.googleapis.com/oauth2/v2/userinfo?alt=json";

    @RequestMapping(value = "/login/google/callback")
    public ModelAndView googleCallback(@RequestParam(value="code", required=false) String verifierCode) {
        Verifier verifier = new Verifier(verifierCode);
        OAuthService service = this.googleServiceProvider.getService();
        Token accessToken = service.getAccessToken(OAuthConstants.EMPTY_TOKEN, verifier);
        OAuthRequest request = new OAuthRequest(Verb.GET, GOOGLE_RESOURCE);
        service.signRequest(accessToken, request);
        Response response = request.send();
        String responseBody = response.getBody();
        logger.info("Google response body: " + responseBody);

        SocialUserDTO socialUser = this.googleUserService.parseResponse(responseBody);
        this.googleUserService.signIn(socialUser);

        return new ModelAndView("redirect:/authRedirect");
    }
}