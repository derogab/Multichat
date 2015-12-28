/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientdata;

import java.net.*;
import java.io.*;

/**
 *
 * @author Gabriele De Rosa
 */
public class ClientData {
     public static void main(String[] args) {
         Socket Connessione=new Socket ();
         // Parametri di connessione;
         String Server="localhost";
         int Porta= 3333;
         // Stringa per la gestione del flusso di input
         InputStream in ;
         InputStreamReader n;
         BufferedReader c;
         OutputStream outt;
         PrintWriter pw;
         String messaggio_ricevuto;
         String messaggio_inviato;
         
         try{ // Tentativo di connessione
             Connessione= new Socket(Server,Porta);
             System.out.println("Connessione Stabilita");
         }
         catch(Exception e){
             System.err.println(e);
             
         }
           
         
         try{
             // Gestione del flusso di input 
             in = Connessione.getInputStream();
             n = new InputStreamReader(in);
             c = new BufferedReader(n);
             // Gestione del flusso di uscita
             outt = Connessione.getOutputStream();
             pw = new PrintWriter(outt);
             // Gestione flusso da tastiera
             InputStreamReader input = new InputStreamReader(System.in);
             BufferedReader tastiera = new BufferedReader(input);
             System.out.println("Inizio Chat:");
             while(true){
                 // Messaggio letto da tastiera
                 messaggio_inviato = tastiera.readLine();
                 if(messaggio_inviato.equals("fine")){
                     break;
                 }
                 
                 // Invia il messaggio
                 pw.println(messaggio_inviato);
                 pw.flush();
                 
                 // Messaggio ricevuto
                 messaggio_ricevuto = c.readLine();
                 System.out.println(">> "+messaggio_ricevuto);
                 
             }
             
             // Chiusura dei flussi in ricezione e della connessione
             c.close();
             Connessione.close();
             System.out.println("Connessione chiusa");
            
             
         }
         
         catch(Exception e){
             System.err.println(e);
             
         }
              
         
     }
    }