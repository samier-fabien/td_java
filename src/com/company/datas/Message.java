package com.company.datas;

import java.util.ArrayList;
import java.util.List;

public class Message {
    String decryptedKey;
    String encryptedKey;
    List<String> originalMessage = new ArrayList<String>();
    List<String> encodedMessage = new ArrayList<String>();

    public String getDecryptedKey() {
        return decryptedKey;
    }

    public void setDecryptedKey(String decryptedKey) {
        this.decryptedKey = decryptedKey;
    }

    public String getEncryptedKey() {
        return encryptedKey;
    }

    public void setEncryptedKey(String encryptedKey) {
        this.encryptedKey = encryptedKey;
    }

    public List<String> getOriginalMessage() {
        return originalMessage;
    }

    public List<String> getEncodedMessage() {
        return encodedMessage;
    }

    public void setOriginalMessage(List<String> originalMessage) {
        this.originalMessage = originalMessage;
    }

    public void setEncodedMessage(List<String> encodedMessage) {
        this.encodedMessage = encodedMessage;
    }
}
