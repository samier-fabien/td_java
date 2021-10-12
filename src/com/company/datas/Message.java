package com.company.datas;

import org.apache.commons.lang3.StringUtils;

public class Message {
    String originalMessage;
    String encryptedString;
    String decryptedString;
    String encodedString;
    String DecodedString;

    public String getOriginalMessage() {
        return originalMessage;
    }

    public void setOriginalMessage(String originalMessage) {
        this.originalMessage = StringUtils.stripAccents(originalMessage);
    }

    public String getEncryptedString() {
        return encryptedString;
    }

    public void setEncryptedString(String encryptedString) {
        this.encryptedString = encryptedString;
    }

    public String getDecryptedString() {
        return decryptedString;
    }

    public void setDecryptedString(String decryptedString) {
        this.decryptedString = decryptedString;
    }

    public String getEncodedString() {
        return encodedString;
    }

    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }

    public String getDecodedString() {
        return DecodedString;
    }

    public void setDecodedString(String decodedString) {
        DecodedString = decodedString;
    }
}
