package com.oauth.api;

import com.oauth.application.dto.ApplicationDto;
import com.oauth.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.Principal;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
public class ApplicationRestController {

    private final ApplicationService applicationService;
//    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity load(Principal principal) {
        log.info("load(): " + principal.getName());

        return ResponseEntity.ok(applicationService.loadAll(Long.valueOf(principal.getName())));
    }

    @GetMapping("/{name}")
    public ResponseEntity loadDetail(@PathVariable("name") String name) {
        log.info("loadDetailApplication(): " + name);

        return ResponseEntity.ok(applicationService.loadDetail(name));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ApplicationDto applicationDto, Principal principal) {
        log.info("ApplicationRestController - save(): " + applicationDto.getName());
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String clientSecret = passwordEncoder.encode(applicationDto.getName());

        return ResponseEntity.created(URI.create("http://localhost:8080/application"))
                .body(applicationService.save(applicationDto, clientSecret, Long.valueOf(principal.getName())));
    }
}
