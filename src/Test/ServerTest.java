
package Test;
import java.io.*;
import java.net.Socket;
import Server.Server;

public class ServerTest {


    public void testServer() throws IOException {
        Thread serverThread = new Thread(() -> {
            try {
                Server server = new Server();
                server.start(8080); // Port numarası
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        serverThread.start();

        try {
            Thread.sleep(1000); // sunucuyu başlatan iş parçacığını bekleme
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // sunucuya komut gönderme
        try (
                Socket clientSocket = new Socket("localhost", 8080);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            out.println("ls"); // ls => listeleme komudu gönder
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line); // gelen sonuç
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        serverThread.interrupt(); // iş parçacığını bitir


    }
}

