package com.oauth.encryption;

import com.oauth.util.KISA_SEED_CBC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Description : KISA SEED 알고리즘
 * @author leejinho
 * @version 1.0
 */

@Slf4j
@Component
public class SEEDPasswordEncoder implements PasswordEncoder {

    private final byte[] pbszUserKey = "testCrypt2020!@#".getBytes();
    private final byte[] pbszIV = "1234567890123456".getBytes();

    @Override
    public String encode(CharSequence rawPassword) {
        byte[] encryptedPassword = KISA_SEED_CBC.SEED_CBC_Encrypt(pbszUserKey, pbszIV, rawPassword.toString().getBytes(), 0, rawPassword.toString().getBytes().length);
        return "{seed}"+ Arrays.toString(encryptedPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.length() == 0) {
            log.warn("Empty encoded password");
            return false;
        }

        return encodedPassword.equals(encode(rawPassword));
    }
}
