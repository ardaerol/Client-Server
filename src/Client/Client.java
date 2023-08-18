package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345;

        try (
                Socket socket = new Socket(serverAddress, serverPort);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            Scanner scanner = new Scanner(System.in);
            String userInput;
            while ((userInput = scanner.nextLine()) != null) {
                out.println(userInput);
                String response;
                while ((response = in.readLine()) != null) {
                    // Serverdan gelen cevabı ekrana yazdırır
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
