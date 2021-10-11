package com.company;

import com.company.tools.Transcoder;
import com.company.tools.Transcoder2;
import org.apache.commons.lang3.StringUtils;
import org.germain.tool.ManaBox;

public class Main {

    public static void main(String[] args) {
        String originalString = "Le message original et non pas orignal avec un é et un à.";

        originalString = StringUtils.stripAccents(originalString);

        String encryptedString = ManaBox.encrypt(originalString);
        String decryptedString = ManaBox.decrypt(encryptedString);

        System.out.println("Message d'origine : "+originalString);
        System.out.println("Message crypté : "+encryptedString);
        System.out.println("Message décrypté : "+decryptedString);

        //Transcoder transcoder = new Transcoder();
        Transcoder2 transcoder2 = new Transcoder2();
        System.out.println("Résultat encode : "+transcoder2.encode("Petit message"));
        System.out.println("Résultat decode : "+transcoder2.decode("AUAPBGBQBGBNBFAPASASAJAWAP"));
    }
}
