package com.developer.assignment.global.domain;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AES128Cipher {

    private static final String ALG = "AES/CBC/PKCS5Padding";
    private static final String KEY = "aug90s1i183oa346sfuzc37bq251weui";
    private static final String IV_128 = KEY.substring(0, 128 / 8); // 16 byte

    public static String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(ALG);
        SecretKeySpec keySpec = new SecretKeySpec(IV_128.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_128.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);

        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(ALG);
        SecretKeySpec keySpec = new SecretKeySpec(IV_128.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_128.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

}
