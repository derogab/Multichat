/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multichatclient;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Gabriele De Rosa
 */

public class MultichatClient
{
	public static void main(String[] args){
		try
		{
			Scanner scanner = new Scanner(System.in);
			System.out.print("Inserisci il tuo Username: ");
			String name = scanner.nextLine();
			System.out.print("IP del server: ");
			String serverIp = scanner.nextLine();
			Socket socket=new Socket(serverIp,7777);

			System.out.println("Connessione completata");

			Thread sender = new Thread(new ClientSender(socket, name));
			Thread receiver = new Thread(new ClientReceiver(socket));

			sender.start();
			receiver.start();
		}
		catch (Exception e)
		{
		}
	}

	static class ClientSender extends Thread
	{
		Socket socket;
		DataOutputStream out;
		String name;

		ClientSender(Socket socket,String name){
			this.socket=socket;
			try
			{
				out = new DataOutputStream(socket.getOutputStream());
				this.name=name;
			}
			catch(Exception e){}
		}



		public void run(){
			Scanner scanner = new Scanner(System.in);
			try
			{	
				if (out!=null)
				{
					out.writeUTF(name);
				}

				while(out !=null){
					out.writeUTF("["+name+"]"+scanner.nextLine());
				}

				
			}
			catch (IOException e)
			{
			}
		}
	}

	static class ClientReceiver extends Thread
	{
		Socket socket;
		DataInputStream in;

		ClientReceiver(Socket socket){
			this.socket = socket;
			try
			{
				in = new DataInputStream(socket.getInputStream());
			}
			catch (IOException e)
			{
			}
		}

		public void run(){
			try
			{
				while(true){
				System.out.println('\n'+in.readUTF()+'\n');
				}
			}
			catch (IOException e)
			{
			}
		}
	}
}