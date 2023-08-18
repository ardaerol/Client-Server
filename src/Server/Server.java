package Server;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Command.Command;

public class Server {
    public static void main(String[] args) {
        int portNumber = 8080;
        ExecutorService executorService = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server.Server is listening on port " + portNumber);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());


                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        }
    }
}


class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            Command command = new Command();
            String cmd;
            while ((cmd = in.readLine()) != null) {
                List<String> output = command.execute(cmd);
                for (String line : output) {
                    out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


