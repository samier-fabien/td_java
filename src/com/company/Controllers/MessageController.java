package com.company.Controllers;

import com.company.Ihm.IOConsole;
import com.company.datas.Message;
import com.company.tools.Transcoder2;
import org.germain.tool.ManaBox;

import java.util.InputMismatchException;

public class MessageController {
    public final static String PASCOMPRIS = "Nous n'avons pas compris votre réponse... ";
    public final static String CLEFPASBONNE = "La clef que vous nous avez fournie n'est pas une clef correcte.";
    Message message = new Message();
    IOConsole view = new IOConsole();
    Transcoder2 transcoder = new Transcoder2();

    public MessageController() {
        Message message = new Message();
        IOConsole view = new IOConsole();
        Transcoder2 transcoder = new Transcoder2();
        init();
    }

    public void init() {
        int task;

        do {

            try {
                task = view.askForTask();
            } catch (InputMismatchException e) {
                task = -1;
            }

            switch (task) {
                case 0 -> view.sayGoodbye();
                case 1 -> decode();
                case 2 -> encode();
                default -> view.owlala(PASCOMPRIS);
            }
        } while (task != 0);
    }

    public void encode() {
        if (encryptKey()) {
            message.setOriginalMessage(view.askForMessage());
            message.setEncodedMessage(transcoder.encode(message.getOriginalMessage()));

            view.displayAfterEncryption(message.getOriginalMessage(), message.getEncodedMessage(), message.getEncryptedKey(), message.getDecryptedKey());
        }
    }

    public boolean encryptKey() {
        String key = view.askForKey();
        if (!key.equals("")) {
            message.setDecryptedKey(key);
        } else {
            message.setDecryptedKey("CFfrkowl.aDzyS:eHjsGPZgMApWvRYVmtnK!BuU IQiEXTxbqhLdNJO,'c");
        }
        message.setEncryptedKey(ManaBox.encrypt(message.getDecryptedKey()));

        return true;
    }

    public void decode() {
        if (decryptKey()) {
            message.setEncodedMessage(view.askForMessage());
            message.setOriginalMessage(transcoder.decode(message.getEncodedMessage()));
            view.displayAfterEncryption(message.getOriginalMessage(), message.getEncodedMessage(), message.getEncryptedKey(), message.getDecryptedKey());
        }
    }

    public boolean decryptKey() {
        String key = view.askForKey();
        try {
            message.setDecryptedKey(ManaBox.decrypt(message.getEncryptedKey()));
        } catch (IllegalArgumentException e) {
            view.owlala(CLEFPASBONNE);
            return false;
        }
        return true;
    }
}