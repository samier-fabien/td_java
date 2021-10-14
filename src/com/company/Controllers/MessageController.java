package com.company.Controllers;

import com.company.Ihm.IOConsole;
import com.company.datas.Message;
import com.company.tools.Transcoder2;
import org.apache.commons.lang3.StringUtils;
import org.germain.tool.ManaBox;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.InputMismatchException;
import java.lang.IllegalArgumentException;
import java.util.List;

public class MessageController {
    public final static String PASCOMPRIS = "Nous n'avons pas compris votre réponse... ";
    public final static String CLEFPASBONNE = "La clef que vous nous avez fourni n'est pas une clef correcte.";
    public final static String CHEMININVALIDE = "Le chemin invalide : le fichier n'a pas été trouvé.";
    public final static String IOPROBLEME = "Désolé, un problème est survenu, veuillez répéter l'opération.";

    Message message = null;//new Message();
    IOConsole view = new IOConsole();
    Transcoder2 transcoder = new Transcoder2();
    boolean method = true;
    Path pathToDecoded;
    Path pathToEncoded;
    Path pathToKey;

    public MessageController() {
        //Message message = new Message();
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
        message = new Message();
        chooseMethod();
        if (method) {
            encryptKey();
            message.getOriginalMessage().add(view.askForMessage());
            message.getEncodedMessage().add(transcoder.encode(message.getOriginalMessage().get(0)));
            view.displayAfterEncryption(message.getOriginalMessage().get(0), message.getEncodedMessage().get(0), message.getEncryptedKey(), message.getDecryptedKey());
        } else {
            setPathToDecoded();
            setPathToEncoded();
            setPathToKey();
            encryptKey();

            try {
                message.setOriginalMessage(Files.readAllLines(pathToDecoded));
            } catch (IOException e) {
                view.owlala(e.getMessage());
            }

            for (String line : message.getOriginalMessage()) {
                message.getEncodedMessage().add(transcoder.encode(StringUtils.stripAccents(line)));
            }

            write(message.getEncodedMessage(), pathToEncoded);
            write(message.getEncryptedKey(), pathToKey);

            view.displaySuccess();
        }
    }

    public void decode() {
        message = new Message();
        chooseMethod();
        if (method) {
            decryptKey();
            message.getEncodedMessage().add(view.askForMessage());
            message.getOriginalMessage().add(transcoder.decode(message.getEncodedMessage().get(0)));
            view.displayAfterEncryption(message.getOriginalMessage().get(0), message.getEncodedMessage().get(0), message.getEncryptedKey(), message.getDecryptedKey());
        } else {
            setPathToDecoded();
            setPathToEncoded();
            setPathToKey();
            decryptKey();

            try {
                message.setEncodedMessage(Files.readAllLines(pathToEncoded));
            } catch (IOException e) {
                view.owlala(e.getMessage());
            }

            for (String line : message.getEncodedMessage()) {
                message.getOriginalMessage().add(transcoder.decode(StringUtils.stripAccents(line)));
            }

            write(message.getOriginalMessage(), pathToDecoded);
            write(message.getDecryptedKey(), pathToKey);

            view.displaySuccess();
        }
    }

    public void encryptKey() {
        String key = view.askForKey();

        if (!key.equals("")) {
            message.setDecryptedKey(key);
        } else {
            message.setDecryptedKey("CFfrkowl.aDzyS:eHjsGPZgMApWvRYVmtnK!BuU IQiEXTxbqhLdNJO,'c");
        }
        transcoder.init(message.getDecryptedKey());
        message.setEncryptedKey(ManaBox.encrypt(message.getDecryptedKey()));
    }

    public void decryptKey() {
        String key = new String();
        boolean encore = true;
        //Contrôle si String est bien une base 64 pour éviter erreur de Manabox.decrypt
        Base64.Decoder decoder = Base64.getDecoder();
        while(encore) {
            try {
                key = view.askForKey();
                decoder.decode(key);
                encore = false;
            } catch (IllegalArgumentException e) {
                view.owlala(CLEFPASBONNE);
            }
        }

        message.setEncryptedKey(key);
        message.setDecryptedKey(ManaBox.decrypt(message.getEncryptedKey()));
    }

    public void chooseMethod() {
        boolean encore = true;
        int method = 0;

        do {
            try {
                method = view.askForMethod();
                encore = false;
            } catch (InputMismatchException e) {
                view.owlala(PASCOMPRIS);
            }
        } while (encore);
        this.method = method == 0;
    }

    public void setPathToDecoded() {
        boolean encore = true;

        do {
            String givenPath = view.askForPathToDecoded();
            Path path = Paths.get(givenPath);
            if (Files.exists(path)) {
                pathToDecoded = Paths.get(givenPath);
                encore = false;
            } else {
                view.owlala(CHEMININVALIDE);
            }
        } while (encore);
    }

    public void setPathToEncoded() {
        boolean encore = true;

        do {
            String givenPath = view.askForPathToEncoded();
            Path path = Paths.get(givenPath);
            if (Files.exists(path)) {
                pathToEncoded = Paths.get(givenPath);
                encore = false;
            } else {
                view.owlala(CHEMININVALIDE);
            }
        } while (encore);
    }

    public void setPathToKey() {
        boolean encore = true;

        do {
            String givenPath = view.askForPathToKey();
            Path path = Paths.get(givenPath);
            if (Files.exists(path)) {
                pathToKey = Paths.get(givenPath);
                encore = false;
            } else {
                view.owlala(CHEMININVALIDE);
            }
        } while (encore);
    }

    public void write(List<String> liste, Path path) {
        for (String line : liste) {
            try {
                Files.writeString(path, line+System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                view.owlala(IOPROBLEME);
                return;
            }
        }
    }

    public void write(String str, Path path) {
        try {
            Files.writeString(path, str, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            view.owlala(IOPROBLEME);
            return;
        }
    }
}