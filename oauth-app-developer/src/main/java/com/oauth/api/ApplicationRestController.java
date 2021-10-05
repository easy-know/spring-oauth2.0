package com.oauth.api;

import com.oauth.application.dto.ApplicationDto;
import com.oauth.application.service.ApplicationService;
import com.oauth.util.AES256;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok(applicationService.loadAll(Long.valueOf(principal.getName())));
    }

    @GetMapping("/{name}")
    public ResponseEntity loadDetail(@PathVariable("name") String name) {
        return ResponseEntity.ok(applicationService.loadDetail(name));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ApplicationDto applicationDto, Principal principal) throws Exception {
        log.info("save() - principal.getName(): " + principal.getName());

        if (applicationService.countApplicationByDeveloper(Long.valueOf(principal.getName())) > 5) {
            return ResponseEntity.unprocessableEntity().body("계정당 애플리케이션 5개만 만들 수 있습니다.");
        }

//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        String clientSecretUuid = UUID.randomUUID().toString().replaceAll("-", "");

//        String clientSecret = passwordEncoder.encode(clientSecretUuid);

//        return ResponseEntity.created(URI.create("http://localhost:8080/application"))
//                .body(applicationService.save(applicationDto, clientSecret, Long.valueOf(principal.getName())));
        return ResponseEntity.created(URI.create("http://localhost:8080/application"))
                .body(applicationService.save(applicationDto, Long.valueOf(principal.getName())));
    }
}
