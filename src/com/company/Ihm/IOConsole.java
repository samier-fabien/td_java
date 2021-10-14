package com.company.Ihm;

import com.company.Controllers.MessageController;


import java.util.Scanner;

public class IOConsole {

    public int askForTask() {
        int choice;
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ Vous voulez :  decrypter un message (1), crypter un message (2), quitter le programme (0) ? ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════╝");

            choice = scanner.nextInt();

        return choice;
    }

    public String askForMessage() { //Veuillez saisir le message à traiter :
        String msg;
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║ Veuillez saisir le message à traiter ║");
        System.out.println("╚══════════════════════════════════════╝");
        msg = scanner.nextLine();

        return msg;
    }

    public String askForKey() {
        String key = new String();
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ Veuillez saisir une clé d'encodage, cette clé doit contenir tous les caractères à encoder dans n'importe quel ordre : ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        try {
            key = scanner.nextLine();
        } catch (IllegalArgumentException e) {
            owlala(MessageController.CLEFPASBONNE);
            System.exit(0);
        }

        return key;
    }

    public void displayAfterEncryption(String original, String encode, String encrypted, String decrypted) {
        System.out.println("╔════════════╗");
        System.out.println("║ Résultat : ║");
        System.out.println("╚════════════╝");
        System.out.println("Message original : "+original);
        System.out.println("Message encodé : "+encode);
        System.out.println("Clef cryptée : "+encrypted);
        System.out.println("Clef décryptée : "+decrypted);
        System.out.println("");
    }

    public void sayGoodbye(){
        System.out.println("╔══════════════════════════╗");
        System.out.println("║ A plus tard sur TINACC ! ║");
        System.out.println("╚══════════════════════════╝");
    }

    public void owlala(String msg) {
        System.out.println("                   "+msg);
        System.out.println("               ?");
        System.out.println("       , ___ ?");
        System.out.println("     `\\/{O,o}");
        System.out.println("      / /)  )");
        System.out.println("     /,--\"-\"-");
    }

    public int askForMethod() {
        int method;
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ Voulez vous utiliser le programme avec la console (0) ou à partir de fichiers texte (1) ? ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════╝");
        method = scanner.nextInt();

        return method;
    }

    public String askForPathToDecoded() {
        String path;
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ Veuillez saisir le chemin du fichier contenant ou qui contiendra le texte décodé : ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝");
        path = scanner.nextLine();

        return path;
    }

    public String askForPathToEncoded() {
        String path;
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ Veuillez saisir le chemin du fichier contenant ou qui contiendra le texte encodé : ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝");
        path = scanner.nextLine();

        return path;
    }

    public String askForPathToKey() {
        String path;
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ Veuillez saisir le chemin du fichier contenant ou qui contiendra la clef : ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        path = scanner.next();

        return path;
    }

    public void displaySuccess() {
        System.out.println("▄▄█████████████████████████████─ Succès !");
        System.out.println("▀▀▀───▀█▄▀▄▀████▀──▀█▄▀▄▀████▀── Vous trouverez le résultat");
        System.out.println("────────▀█▄█▄█▀──────▀█▄█▄█▀──── dans les différents fichiers.");
    }
}
