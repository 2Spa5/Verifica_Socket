package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread {
    Socket s;
    Biglietti b;

    public MioThread(Socket s, Biglietti b) {
        this.s = s;
        this.b = b;
    }

    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            do {

                String richiesta = in.readLine();
                System.out.println("ho ricevuto --> " + richiesta);

                switch (richiesta) {
                    case "A":
                    case "a":
                        if (b.getBiglietti() == 0) {
                            out.writeBytes("Acquisto non effettuato: i biglietti sono finiti\n");
                        }else{
                            out.writeBytes("Biglietto acquistato\n");
                            b.setBiglietti(b.getBiglietti()-1);
                        }
                    break;

                    case "D":
                    case "d":

                        out.writeBytes("Sono disponibili " + b.getBiglietti() + " biglietti\n");

                    break;

                    case "Q":
                    case "q":

                        System.out.println("Utente Scollegato\n");
                        out.writeBytes("Sei uscito\n");
                        s.close();

                    break;
                    
                    default:
                        out.writeBytes("Errore: Input sbagliato\n");
                    break;
                }

            } while (b.getBiglietti() > 0);

            out.writeBytes("I biglietti sono terminati\n");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
