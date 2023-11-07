package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 3000);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Digita D per visualizzare la disponibilità o A per acquistare un biglietto e Q per uscire");

            String richiesta = "";
            String risposta = "";

            do {
                richiesta = scanner.nextLine();
                out.writeBytes( richiesta + "\n");
                risposta = in.readLine();
                System.out.println(risposta);
            } while (!risposta.equals("q") || !risposta.equals("Q"));

            scanner.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Qualcosa è andato storto");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}