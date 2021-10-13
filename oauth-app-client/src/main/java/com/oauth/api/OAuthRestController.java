package com.oauth.api;

import com.google.gson.Gson;
import com.oauth.model.OAuthToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2")
public class OAuthRestController {

    private final Gson gson;
    private final RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.registration.gsitm.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.gsitm.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.gsitm.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.provider.gsitm.token-uri}")
    private String tokenUri;

    @GetMapping(value = "/callback")
    public ResponseEntity callbackSocial(HttpServletRequest request, @RequestParam(required = false) String code, HttpServletResponse response) throws URISyntaxException {
        log.info(request.getQueryString());
        log.info("code: " + code);

        String credentials = clientId+":"+clientSecret;
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "Basic " + encodedCredentials);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", redirectUri);

        return getOAuthToken(headers, params, response);
    }

    private ResponseEntity getOAuthToken(HttpHeaders headers,
                                         MultiValueMap<String, String> params,
                                         HttpServletResponse httpServletResponse) throws URISyntaxException {

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUri, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {

            OAuthToken oAuthToken = gson.fromJson(response.getBody(), OAuthToken.class);

            log.info(oAuthToken.getAccess_token());
            Cookie cookieAccessToken = new Cookie("accessToken", oAuthToken.getAccess_token());
            cookieAccessToken.setMaxAge((int) oAuthToken.getExpires_in());
            cookieAccessToken.setPath("/");
            httpServletResponse.addCookie(cookieAccessToken);

            log.info(oAuthToken.getRefresh_token());
            Cookie cookieRefreshToken = new Cookie("refreshToken", oAuthToken.getRefresh_token());
            cookieRefreshToken.setPath("/");
            httpServletResponse.addCookie(cookieRefreshToken);

//            return ResponseEntity.ok().body(gson.fromJson(response.getBody(), OAuthToken.class));

            URI redirectUri = new URI("/user");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirectUri);
            httpHeaders.add("accessToken", oAuthToken.getAccess_token());
            httpHeaders.add("refreshToken", oAuthToken.getRefresh_token());

            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
//            return gson.fromJson(response.getBody(), OAuthToken.class);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/token/refresh")
    public ResponseEntity refreshToken(@RequestParam String refreshToken, HttpServletResponse response) throws URISyntaxException {

        String credentials = clientId+":"+clientSecret;
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "Basic " + encodedCredentials);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("refresh_token", refreshToken);
        params.add("grant_type", "refresh_token");

        return getOAuthToken(headers, params, response);
    }
}
