package com.oauth.api;

import com.oauth.oauth2.client_details.dto.OAuthClientDetailsDto;
import com.oauth.oauth2.client_details.service.OAuthClientDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth2")
public class OAuth2RestController {

    private final OAuthClientDetailsService oAuthClientDetailsService;

    @GetMapping("{clientId}")
    public ResponseEntity loadDetail(@PathVariable("clientId") String clientId) throws Exception {
        log.info("loadOAuthDetails(): " + clientId);
        return ResponseEntity.ok(oAuthClientDetailsService.loadDetail(clientId));
    }

    @PostMapping("/redirect/{id}")
    public ResponseEntity saveRedirect(@PathVariable String id, @RequestBody OAuthClientDetailsDto oAuthClientDetailsDto) {
        log.info("saveRedirect(): " + oAuthClientDetailsDto.getWebServerRedirectUri());
        return ResponseEntity.ok(oAuthClientDetailsService.saveRedirectUrl(Long.valueOf(id), oAuthClientDetailsDto.getWebServerRedirectUri()));
    }

    @PutMapping("{id}}")
    public ResponseEntity updateRedirect(@PathVariable String id, @RequestBody OAuthClientDetailsDto oAuthClientDetailsDto) {
        log.info("saveRedirect(): " + oAuthClientDetailsDto.getWebServerRedirectUri());
        return ResponseEntity.ok(oAuthClientDetailsService.update(Long.valueOf(id), oAuthClientDetailsDto.getWebServerRedirectUri()));
    }

    @DeleteMapping("{id}}")
    public ResponseEntity updateRedirect(@PathVariable String id) {
        oAuthClientDetailsService.delete(Long.valueOf(id));
        return ResponseEntity.ok().build();
    }
}