package com.oauth.api;

import com.oauth.application.dto.ApplicationDto;
import com.oauth.application.service.ApplicationService;
import com.oauth.encryption.SEEDPasswordEncoder;
import com.oauth.util.AES256;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * Description : Application Rest Controller
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
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> load(Principal principal) {
        return ResponseEntity.ok(applicationService.loadAll(Long.valueOf(principal.getName())));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ApplicationDto> loadDetail(@PathVariable("name") String name, Principal principal) {
        return ResponseEntity.ok(applicationService.loadDetail(name, Long.valueOf(principal.getName())));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ApplicationDto applicationDto, Principal principal) {

        if (applicationService.countApplicationByDeveloper(Long.valueOf(principal.getName())) > 5) {
            return ResponseEntity.unprocessableEntity().body("계정당 애플리케이션 5개만 만들 수 있습니다.");
        }

        String clientSecretUuid = UUID.randomUUID().toString().replaceAll("-", "");
        String clientSecret = passwordEncoder.encode(clientSecretUuid);

        return ResponseEntity.created(URI.create("http://localhost:8080/application"))
                .body(applicationService.save(applicationDto, clientSecretUuid, clientSecret, Long.valueOf(principal.getName())));
    }

    @PostMapping("aes")
    public ResponseEntity<String> saveAes(@RequestBody ApplicationDto applicationDto, Principal principal) throws Exception {

        if (applicationService.countApplicationByDeveloper(Long.valueOf(principal.getName())) > 5) {
            return ResponseEntity.unprocessableEntity().body("계정당 애플리케이션 5개만 만들 수 있습니다.");
        }

        AES256 aes256 = new AES256();

        String clientSecretUuid = UUID.randomUUID().toString().replaceAll("-", "");
        String clientSecret = aes256.encrypt(clientSecretUuid);

        return ResponseEntity.created(URI.create("http://localhost:8080/application"))
                .body(applicationService.save(applicationDto, clientSecretUuid, clientSecret, Long.valueOf(principal.getName())));
    }

    @PostMapping("seed")
    public ResponseEntity<String> saveSeed(@RequestBody ApplicationDto applicationDto, Principal principal) throws Exception {

        if (applicationService.countApplicationByDeveloper(Long.valueOf(principal.getName())) > 5) {
            return ResponseEntity.unprocessableEntity().body("계정당 애플리케이션 5개만 만들 수 있습니다.");
        }

        SEEDPasswordEncoder seedPasswordEncoder = new SEEDPasswordEncoder();

        String clientSecretUuid = UUID.randomUUID().toString().replaceAll("-", "");
        String clientSecret = seedPasswordEncoder.encode(clientSecretUuid);
        log.info("clientSecret: " + clientSecret);

        return ResponseEntity.created(URI.create("http://localhost:8080/application"))
                .body(applicationService.save(applicationDto, clientSecretUuid, clientSecret, Long.valueOf(principal.getName())));
    }
}
