package Command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Command {
    private Process process;
    private BufferedReader input;
    private PrintWriter output;
    private PrintWriter error;

    public Command() {
        try {
            process = new ProcessBuilder().redirectErrorStream(true).start();
            input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            output = new PrintWriter(process.getOutputStream(), true);
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                System.err.println(errorLine);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized List<String> execute(String command) {
        List<String> outputLines = new ArrayList<>();

        try {
            process.getOutputStream().flush();
            output.println(command);

            String line;
            while ((line = input.readLine()) != null) {
                outputLines.add(line); // Çıktıyı Listeye ekler
            }

            process.getOutputStream().close();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return outputLines; // Çıktı listesini cmd döndürür
    }

}

