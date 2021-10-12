package com.company.Ihm;

import com.company.Controllers.MessageController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IOConsole {

    public int askForTask() {
        int choice;
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ Vous voulez : '1' decrypter un message, '2' crypter un message, '0' quitter le programme ? ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════╝");

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
        String key;
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ Veuillez saisir une clé d'encodage, cette clé doit contenir tous les caractères à encoder dans n'importe quel ordre : ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        key = scanner.next();

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

    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    public void sayGoodbye(){
        System.out.println("╔══════════════════════════╗");
        System.out.println("║ A plus tard sur TINACC ! ║");
        System.out.println("╚══════════════════════════╝");
    }

    public void owlala(String msg) {
        System.out.println("");
        System.out.println("              ?");
        System.out.println("       , ___?");
        System.out.println("     `\\/{O,o}");
        System.out.println("      / /)  )");
        System.out.println("     /,--\"-\"-");
        System.out.println(msg);
    }
}
