package raposalab;

import java.net.*;
import java.io.*;

/**
 *
 * @author Zarathustra aka Kevin Baik
 */
public class Server extends Thread {

  private ServerSocket serverSocket;

  public Server(int port) throws IOException {
    serverSocket = new ServerSocket(port);
  }

  public void run() {
    try {
      Socket client = serverSocket.accept();

      DataInputStream in = new DataInputStream(client.getInputStream());
      BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
      DataOutputStream out = new DataOutputStream(client.getOutputStream());

      while (true) {
        String message = in.readUTF();
        System.out.println(message);
        System.out.print("Enter response: ");
        String response = console.readLine();

        out.writeUTF(response);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    //int port = Integer.parseInt(args[0]);

    try {
      //Thread t = new Server(5001);
      Thread t = new Server(5003);
      
      t.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
