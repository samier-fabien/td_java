package com.company;

import com.company.Controllers.MessageController;
import com.company.tools.Transcoder;
import com.company.tools.Transcoder2;
import org.apache.commons.lang3.StringUtils;
import org.germain.tool.ManaBox;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        splashScreen();
        MessageController app = new MessageController();

        /*
        /home/magash/test/decoded.txt
        /home/magash/test/encoded.txt
        /home/magash/test/key.txt
        CFfrkowl.aDzyS:eHjsGPZgMApWvRYVmtnK!BuU IQiEXTxbqhLdNJO,'c
        6Qe0IsEEH1utWRe7UKzGMiDTytOB3HS1dEfIB4imna3IRHXHRn5ZrvKFEcPjmPgKYGuytG+gDAl1m2DdHalJQg==
         */
    }

    public static void splashScreen() {
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║   ___________  ___  ___   ___  _______   _____   _____  ║");
        System.out.println("║  /____   ___/ /  / /  |  /  / /  __   / /  __/  /  __/  ║");
        System.out.println("║      /  /    /  / /   | /  / /  / /  / /  /    /  /     ║");
        System.out.println("║     /  /    /  / /    |/  / /  /_/  / /  /    /  /      ║");
        System.out.println("║    /  /    /  / /  /|    / /  __   / /  /    /  /       ║");
        System.out.println("║   /  /    /  / /  / |   / /  / /  / /  /__  /  /__      ║");
        System.out.println("║  /__/    /__/ /__/  |__/ /__/ /__/ /_____/ /_____/      ║");
        System.out.println("╠═════════════════════════════════════════════════════════╣");
        System.out.println("║  TINACC : This Is Not A Caesar Code  (v1.0 12/10/21)    ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
    }
}
