package com.example;

import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Server Avviato");
            ServerSocket server = new ServerSocket(3000);
            MioThread t = null;
            Biglietti b = new Biglietti(5);

            while(true){
                Socket s = server.accept();
                t = new MioThread(s, b);
                t.start();
                System.out.println("\nNuovo Utente");
            }

        } catch (Exception e) {
            System.out.println("Qualcosa è andato storto");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}