package com.softserve.edu.delivery.service.social.provider.api.util;

import com.softserve.edu.delivery.service.social.dto.SocialUserDTO;
import org.json.JSONObject;

public class ResponseParser {

    public static SocialUserDTO fromGoogle(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);

        SocialUserDTO socialUser = new SocialUserDTO();
        socialUser.setId(jsonObject.getString("id"));
        socialUser.setEmail(jsonObject.getString("email"));
        socialUser.setFirstName(jsonObject.getString("given_name"));
        socialUser.setLastName(jsonObject.getString("family_name"));
        socialUser.setPhotoUrl(jsonObject.getString("picture"));
        socialUser.setGender(jsonObject.getString("gender"));

        return socialUser;
    }

    public static SocialUserDTO fromLinkedIn(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);

        SocialUserDTO socialUser = new SocialUserDTO();
        socialUser.setId(jsonObject.getString("id"));
        socialUser.setEmail(jsonObject.getString("emailAddress"));
        socialUser.setFirstName(jsonObject.getString("firstName"));
        socialUser.setLastName(jsonObject.getString("lastName"));
        socialUser.setPhotoUrl(jsonObject.getString("pictureUrl"));

        return socialUser;
    }


}
