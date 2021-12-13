# WebViewGameServerpackage com.basic;


Server:






    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.io.OutputStreamWriter;
    import java.net.ServerSocket;
    import java.net.Socket;
    
    public class Main {
    private static Socket clientSocket;
    private static BufferedWriter out;
    private static String answer = "false";


    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(4004)) {
            while (true) {
                clientSocket = server.accept();
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                out.write(answer);
                out.flush();
                System.out.println("Answer is pushed");
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
         }
         }
     }

