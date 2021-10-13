package com.company.Controllers;

import com.company.Ihm.IOConsole;
import com.company.datas.Message;
import com.company.tools.Transcoder2;
import org.apache.commons.lang3.StringUtils;
import org.germain.tool.ManaBox;

import java.io.File;
import java.io.IOException;
import java.net.URI;
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
        //init();
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
            message.getfOriginalMessage().add(view.askForMessage());
            message.getfEncodedMessage().add(transcoder.encode(message.getfOriginalMessage().get(0)));
            view.displayAfterEncryption(message.getfOriginalMessage().get(0), message.getfEncodedMessage().get(0), message.getEncryptedKey(), message.getDecryptedKey());
        } else {
            setPathToDecoded();
            setPathToEncoded();
            setPathToKey();
            encryptKey();

            lookForKey(message.getDecryptedKey(), pathToKey);
            System.out.println(message.getDecryptedKey());

            fillList(message.getfOriginalMessage(), pathToEncoded);
            for (String line : message.getfOriginalMessage()) {
                message.getfEncodedMessage().add(transcoder.encode(StringUtils.stripAccents(line)));
            }
            System.out.println("**************DONNEES:***************");
            System.out.println(message.getfOriginalMessage().toString());
            System.out.println(message.getfEncodedMessage().toString());

            write(message.getfEncodedMessage(), pathToEncoded);

            view.displaySuccess();
        }
    }

    public void decode() {
        message = new Message();
        chooseMethod();
        if (method) {
            decryptKey();
            message.getfEncodedMessage().add(view.askForMessage());
            message.getfOriginalMessage().add(transcoder.decode(message.getfEncodedMessage().get(0)));
            view.displayAfterEncryption(message.getfOriginalMessage().get(0), message.getfEncodedMessage().get(0), message.getEncryptedKey(), message.getDecryptedKey());
        } else {
            setPathToDecoded();
            setPathToEncoded();
            setPathToKey();

            lookForKey(message.getDecryptedKey(), pathToKey);
            System.out.println(message.getDecryptedKey());

            fillList(message.getfEncodedMessage(), pathToDecoded);
        }
    }

    public void encryptKey() {
        String key = view.askForKey();

        if (!key.equals("")) {
            message.setDecryptedKey(key);
            transcoder.init(key);
        } else {
            message.setDecryptedKey("CFfrkowl.aDzyS:eHjsGPZgMApWvRYVmtnK!BuU IQiEXTxbqhLdNJO,'c");
        }
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

    public void fillList(List<String> list, Path path) {
        try {
            list = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : list) {
            System.out.println(line);
        }
    }

    public void lookForKey(String key, Path path) {
        try {
            key = String.valueOf(Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void testWrite() {
        List<String> liste = new ArrayList<String>();
        liste.add("Petite phrase");
        liste.add("Deuxieme phrase");
        liste.add("derniere phrase");

        String givenPath = "/home/magash/test/key.txt";
        Path path = Paths.get(givenPath);


        for (String line : liste) {
            try {
                Files.writeString(path, line+System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                System.out.println("Ca marche");
            } catch (IOException e) {
                System.out.println("Ca marche pas");
            }
        }

    }
}