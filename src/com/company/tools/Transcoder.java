package com.company.tools;

import java.util.HashMap;

public class Transcoder {
    String key = "CFfrkowl.aDzyS:eHjsGPZgMApWvRYVmtnK!BuU IQiEXTxbqhLdNJO,'c";//manque j X
    char[] characters;
    char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    HashMap<String, Character> tab = new HashMap<String, Character>();


    //char[] tab = msg.toCharArray();

    public Transcoder() {
        generate(key);
    }

    public Transcoder(String key) {
        this.key = key;
        generate(this.key);
    }

    public String encode(String msg) {

        return msg;
    }

    public String decode(String msg) {

        return msg;
    }

    public void generate(String msg) {
        this.characters = msg.toCharArray();
        int currentFirst = 0;
        int currentSecond = 0;

        for (int i = 0; i<characters.length; i++) {
            System.out.println(characters[i]);
        }

        for (int i = 0; i<characters.length; i++) {

            currentFirst = i/letters.length;
            currentSecond = i - currentFirst*letters.length;
            //System.out.println("premier : "+ currentFirst +" second : "+ (i - currentFirst*letters.length));

            addToTab(Character.toString(letters[currentFirst])+Character.toString(letters[currentSecond]), characters[i]);
        }


        for (char i : tab.values()) {
            System.out.println(i);
        }
        System.out.println(tab);
    }

    public void addToTab(String string, Character character) {
        tab.put(string, character);
    }
}
