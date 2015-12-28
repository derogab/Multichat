/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multichatserver;

import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author Gabriele De Rosa
 */

public class MultichatServer
{
	HashMap clients;

	MultichatServer(){
		clients = new HashMap();    //hashmap = have key value.
		Collections.synchronizedMap(clients); //synchronized
	}

	public void open(){
		ServerSocket serverSocket = null;
		Socket socket = null;

		try
		{
			serverSocket=new ServerSocket(7777);         //presa Server = 7777
			System.out.println("È stato aperto il server.");

			while(true){
				socket = serverSocket.accept();
				System.out.println("Si ha accesso a["+socket.getInetAddress()+"].");

				S_input thread = new S_input(socket);
				thread.start();
			
			}
				
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	void send(String msg){
		Iterator it = clients.keySet().iterator();
		
		while(it.hasNext()){
			try
			{
				DataOutputStream out = (DataOutputStream)clients.get(it.next());
				out.writeUTF(msg);
			}
			catch (IOException e)
			{
			}
		}
	}
	
	
	public static void main(String[] args){
		new MultichatServer().open();
	}

	class S_input extends Thread
	{
		Socket socket;
		DataInputStream in;
		DataOutputStream out;

		S_input(Socket socket)
		{
			this.socket = socket;
			try
			{
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			}
			catch (IOException e)
			{
			}
		}

		public void run(){
			String name="";
			
			try
			{
				name = in.readUTF();
				send(name+"Sei stato in una posizione.");

				clients.put(name, out);
				System.out.println("Il numero attuale di utenti è "+clients.size());
				
				while(in!=null){
					send(in.readUTF());
				}
			}
			catch (IOException e)
			{

			} finally{
				System.out.println(name+"Guardò il cartellino rosso.");
				send(name+"Guardò il cartellino rosso.");
				clients.remove(name);
			}
		}
	}
}