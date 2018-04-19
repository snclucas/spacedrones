package org.spacedrones.clientserver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A simple Swing-based client for the chat server.  Graphically
 * it is a frame with a text field for entering messages and a
 * textarea to see the whole dialog.
 *
 * The client follows the Chat Protocol which is as follows.
 * When the server sends "SUBMITNAME" the client replies with the
 * desired screen name.  The server will keep sending "SUBMITNAME"
 * requests as long as the client submits screen names that are
 * already in use.  When the server sends a line beginning
 * with "NAMEACCEPTED" the client is now allowed to start
 * sending the server arbitrary strings to be broadcast to all
 * chatters connected to the server.  When the server sends a
 * line beginning with "MESSAGE " then all characters following
 * this string should be displayed in its message area.
 */
public class Client {

  BufferedReader in;
  PrintWriter out;

  /**
   * Constructs the client by laying out the GUI and registering a
   * listener with the textfield so that pressing Return in the
   * listener sends the textfield contents to the server.  Note
   * however that the textfield is initially NOT editable, and
   * only becomes editable AFTER the client receives the NAMEACCEPTED
   * message from the server.
   */
  public Client() {


  }


  /**
   * Connects to the server then enters the processing loop.
   */
  private void run() throws IOException {

    // Make connection and initialize streams
    String serverAddress = "localhost";
    Socket socket = new Socket(serverAddress, 9001);
    in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);

    // Process all messages from server, according to the protocol.
    while (true) {
      String line = in.readLine();
      if (line.startsWith("SUBMITNAME")) {
        out.println("Simon");
      } else if (line.startsWith("NAMEACCEPTED")) {
        //textField.setEditable(true);
      } else if (line.startsWith("MESSAGE")) {
        //messageArea.append(line.substring(8) + "\n");
      }
    }
  }

  /**
   * Runs the client as an application with a closeable frame.
   */
  public static void main(String[] args) throws Exception {
    Client client = new Client();
    client.run();
  }
}