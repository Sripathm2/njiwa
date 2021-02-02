import java.net.*;
import java.io.*;
 
/**
 * This program is a socket client application that connects to a time server
 * to get the current date time.
 *
 * @author www.codejava.net
 */
public class SocketClient {
 
    public static void main(String[] args) {
        String hostname = "192.168.1.12";
        int port = 5443;
 
        try (Socket socket = new Socket(hostname, port)) {

            System.out.println(socket.isConnected());

            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.print("GET /njiwa-0.8/rest/auth/check HTTP/1.1");
            pw.println("Host: localhost:8080");
            pw.println("");
            pw.flush();
 
            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
 
            int character;
            StringBuilder data = new StringBuilder();
 
            while ((character = reader.read()) != -1) {
                data.append((char) character);
            }
 
            System.out.println(data);
 
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}