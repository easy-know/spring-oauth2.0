package com.oauth.encoding;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EncodingTest {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    EncodingTest(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Test
    public void encodeTest() {
        System.out.printf("testSecret : %s", passwordEncoder.encode("testSecret"));
        System.out.println();
    }
}
