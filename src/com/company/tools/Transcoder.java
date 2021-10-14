package com.company.tools;

import java.util.HashMap;

public class Transcoder {
    char[] keyAsChars;
    String key;
    HashMap<Character, String> encode = new HashMap<Character, String>();
    HashMap<String, Character> decode = new HashMap<String, Character>();

    public Transcoder() {}

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

    public void init(String key) {
        this.key = key;
        keyAsChars = this.key.toCharArray();

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

            firstLetter = false;
            encode.put(keyAsChars[i], Character.toString(one)+Character.toString(two));
            decode.put(Character.toString(one)+Character.toString(two), keyAsChars[i]);
        }
    }
}
