package com.tarekkma;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Message:");
            String message = s.next();
            System.out.println("Key:");
            String key = s.next();
            Playfair.enc(message,key);
        }
    }
}
