import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread implements Runnable {
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    private String username;
    private Scanner scan;

    public Client(Socket socket, String username){
        try {
            super.start();
//            this.scan = new Scanner(System.in);
            this.socket = socket;
            this.username = username;
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.pw = new PrintWriter(socket.getOutputStream(), true);
            scan = new Scanner(System.in);
            pw.println(username);
        } catch (UnknownHostException e) {
            System.out.println("Error in Client constructor. UnknownHostException");
        } catch (IOException e){
            System.out.println("Error in Client constructor. IOException");
        }
    }

    public void sendMessage(){
        String mess;
        System.out.println("Type a message");
        while (socket.isConnected()) {
            mess = scan.nextLine();
            pw.println(mess);
        }
    }

    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (socket.isConnected()) {
                        String messageFromGroupChat = br.readLine();
                        System.out.println(messageFromGroupChat);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
