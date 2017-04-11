/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raposalab;

import java.net.*;
import java.io.*;

/**
 *
 * @author Zarathustra
 */
public class Client extends Thread {

  private int port;
  private String host;

  public Client(String host, int port) throws IOException {
    this.host = host;
    this.port = port;
  }

  public static void main(String[] args) {
    //int port = Integer.parseInt(args[1]);
    try {
      //Thread t = new Client("localhost", 5001);
      //Thread a = new Client("localhost", 65419);
      Thread a = new Client("localhost", 5003);
      a.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void run() {
    try {
      Socket socket = new Socket(host, port);

      DataInputStream in = new DataInputStream(socket.getInputStream());
      BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
      DataOutputStream out = new DataOutputStream(socket.getOutputStream());

      while (true) {
        System.out.print("You're now connected!");
        System.out.print("Enter response: ");
        String response = console.readLine();
        out.writeUTF(response);

        String message = in.readUTF();
        System.out.println(message);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
