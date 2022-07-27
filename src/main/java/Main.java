import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 1234);
            Scanner scan = new Scanner(System.in);
            System.out.println("Wprowadz swój nick");
            String username = scan.nextLine();
            Client client = new Client(socket, username);
            client.listenForMessage();
            client.sendMessage();
        } catch (IOException e) {
            System.out.println("Error in main method");
        }

    }
}
