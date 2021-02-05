package com.developer.assignment.global.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CryptoStringConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        String result = null;

        try {
            if (attribute != null) {
                result = AES128Cipher.encrypt(attribute);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        String result = null;

        try {
            if (dbData != null) {
                result = AES128Cipher.decrypt(dbData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
