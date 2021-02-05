package com.developer.assignment.domain;

import com.developer.assignment.global.domain.AES128Cipher;
import com.developer.assignment.test.config.ActiveProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(ActiveProfile.TEST)
public class AES128CipherTest {

    @DisplayName("AES128 암호화 테스트 : 암호화 후 텍스트 불일치 비교")
    @Test
    public void aes128_encrypt() throws Exception {
        String origin = "hello";

        String encryptText = AES128Cipher.encrypt(origin);

        assertNotEquals(origin, encryptText);
    }

    @DisplayName("AES128 암호화 테스트 : 암/복호화 후 텍스트 일치 비교")
    @Test
    public void aes128_encryptAndDecrypt() throws Exception {
        String origin = "world";

        String encryptText = AES128Cipher.encrypt(origin);

        assertEquals(origin, AES128Cipher.decrypt(encryptText));
    }

    @DisplayName("AES128 암호화 테스트 : 전화번호 포맷 길이 확인")
    @Test
    public void aes128_phoneNumberLength() throws Exception {
        String origin = "010-0000-0000";
        String encryptText = AES128Cipher.encrypt(origin);

        assertTrue( encryptText.length() < 40 );
    }

    @DisplayName("AES128 암호화 테스트 : 이메일 포맷 길이 확인")
    @Test
    public void aes128_emailLength() throws Exception {
        String origin = "asduaisoduyasioduaszxczodasuixcvioxc18940127051asuoidsaiosauodasiodasuoidodiuaguyxzucizyiu@gmail.com";
        String encryptText = AES128Cipher.encrypt(origin);

        assertTrue( encryptText.length() < 200 );
    }

    @DisplayName("AES128 암호화 테스트 : 이메일 포맷 길이 확인")
    @Test
    public void aes128_passwordLength() throws Exception {
        String origin = "12345zxcvbASDFG!@#$%12345zxcvbASDFG!@#$%12345zxcvbASDFG!@#$%12345zxcvbASDFG!@#$%12345zxcvbASDFG!@#$%";
        String encryptText = AES128Cipher.encrypt(origin);

        assertTrue( encryptText.length() < 200 );
    }
}
