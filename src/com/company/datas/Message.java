package com.company.datas;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Message {
    String decryptedKey;
    String encryptedKey;
    List<String> fOriginalMessage = new ArrayList<String>();
    List<String> fEncodedMessage = new ArrayList<String>();

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

    public List<String> getfOriginalMessage() {
        return fOriginalMessage;
    }

    public List<String> getfEncodedMessage() {
        return fEncodedMessage;
    }
}
