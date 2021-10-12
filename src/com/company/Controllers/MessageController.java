package com.company.Controllers;

import com.company.Ihm.IOConsole;
import com.company.datas.Message;
import com.company.tools.Transcoder2;
import org.germain.tool.ManaBox;

public class MessageController {
    final static String PASCOMPRIS = "Nous n'avons pas compris votre réponse... ";
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
            task = view.askForTask();

            switch (task) {
                case 0 -> view.sayGoodbye();
                case 1 -> decrypt();
                case 2 -> encrypt();
                default -> view.askForTask(PASCOMPRIS);
            }
        } while (task != 0);
    }

    public void encrypt() {
        String key = view.askForKey();
        if (!key.equals("")) {
            transcoder.setKey(key);
        }

        message.setOriginalMessage(view.askForMessage());
        message.setEncodedString(transcoder.encode(message.getOriginalMessage()));
        message.setEncryptedString(ManaBox.encrypt(message.getEncodedString()));

        view.displayAfterEncryption(message.getOriginalMessage(), message.getEncodedString(), message.getEncryptedString());
    }

    public void decrypt() {
        String key = view.askForKey();
        if (!key.equals("")) {
            transcoder.setKey(key);
        }
        message.setEncryptedString(view.askForMessage());
        message.setDecryptedString(ManaBox.decrypt(message.getEncryptedString()));
        message.setOriginalMessage(transcoder.decode(message.getDecryptedString()));
        message.setEncodedString(transcoder.encode(message.getOriginalMessage()));

        view.displayAfterEncryption(message.getOriginalMessage(), message.getEncodedString(), message.getEncryptedString());
    }
}