/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverdata;
import java.net.*;
import java.io.*;
import java.util.Date;

/**
 *
 * @author Gabriele De Rosa
 */
public class ServerData {
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    ServerSocket s ;
    Socket Connessione= new Socket ();    ;
    int porta=3333;
    InputStreamReader in,input;
    BufferedReader sin,tastiera;
   String messinv,messrcv;
    // Stream per gestire il flusso di Output
    OutputStream OUT ;
    PrintWriter Sauto;
    try { 
        s= new ServerSocket(porta);
        while(true) { 
            System.out.println("In attesa di connessione");
            Connessione= s.accept();
            System.out.println("Connessione Stabilita");
            OUT= Connessione.getOutputStream();
            Sauto= new PrintWriter(OUT);
            //Flusso ingresso del Server 
            in = new InputStreamReader(Connessione.getInputStream());
            sin = new BufferedReader(in);
            // Flusso da tastiera
            input = new  InputStreamReader(System.in);
            tastiera = new BufferedReader(input);
            System.out.println("Chat Iniziata");
            while(true)
            {// Stampa il mess ricevuto
                messrcv = sin.readLine();
                if(messrcv==null)
                { System.out.println("Il cliente ha chiuso la chat");
                break;
                }
                System.out.println(">> "+messrcv);
                // Leggi il mess da tastiera
               messinv= tastiera.readLine();
               // invia il messaggio
                Sauto.println(messinv);
                Sauto.flush();
            }
        }
    }
          catch(Exception e){
        System.err.println(e);
       
                    }
           
// Invio dell'informazione al cliente
            /*
            Sauto.println(info);
            Sauto.close();
            Connessione.close();
        
            
          System.out.println("Connessione chiusa"); */
       
        try
        {
          Connessione.close();
           System.out.println("Connessione chiusa");
        }
        
     
        
        
        
        
        
    
        catch(Exception e){
        System.err.println(e);
        }
                
    }
    
}