package com.company.tools;

import java.util.HashMap;
import java.util.Hashtable;

public class Transcoder2 {

    String key = "CFfrkowl.aDzyS:eHjsGPZgMApWvRYVmtnK!BuU IQiEXTxbqhLdNJO,'c";
    char[] keyAsChars;
    HashMap<Character, String> encode = new HashMap<Character, String>();
    HashMap<String, Character> decode = new HashMap<String, Character>();

    public Transcoder2() {
        keyAsChars = key.toCharArray();
        init();
    }

    public String encode(String str) {
        String result = new String("");
        char[] strAsChars = str.toCharArray();

        for (Character character : strAsChars) {
            result = result+encode.get(character);
        }

        return result;
    }

    public String decode(String str) {
        String result = new String("");
        String[] strAsStrings = str.split("(?<=\\G.{2})");

        for (String string : strAsStrings) {
            result = result+decode.get(string);
        }

        return result;
    }

    public void init() {
        Character one = 'A', two = 'A';
        boolean firstLetter = true;

        for (int i = 0; i < key.length(); i++) {
            if (two == 'Z') {
                one++;
                two = 'A';
                firstLetter = true;
            }
            if (!firstLetter) {
                two++;
            }

            /*
            System.out.println("___________________________________________________");
            System.out.println("i : "+i);
            System.out.println("first time : "+firstLetter);
            System.out.println("one : "+one);
            System.out.println("two : "+two);
            System.out.println("caractere : "+keyAsChars[i]);
            System.out.println("deux lettres : "+Character.toString(one)+Character.toString(two));
            */

            firstLetter = false;
            encode.put(keyAsChars[i], Character.toString(one)+Character.toString(two));
            decode.put(Character.toString(one)+Character.toString(two), keyAsChars[i]);

        }
        //System.out.println("tab encode : "+encode);
        //System.out.println("tab decode : "+decode);
    }
}
