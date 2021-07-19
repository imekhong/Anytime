package com.anytime.root.api.naverLogin;

import javax.servlet.http.HttpSession;

import com.github.scribejava.core.model.OAuth2AccessToken;

public interface NaverLoginService {
	public String getAuthorizationUrl(HttpSession session, String uri);
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state, String uri);
	public String getUserProfile(OAuth2AccessToken oauthToken, String uri);
}
