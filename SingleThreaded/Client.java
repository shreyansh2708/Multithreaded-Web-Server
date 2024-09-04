import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public void run() throws UnknownHostException, IOException{
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        Socket clientSocket = new Socket(address, port);
        PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        toSocket.println("Hello from Client");
        String line = fromSocket.readLine();
        System.out.println("Response from the Socket is" + line);
        toSocket.close();
        fromSocket.close();
        clientSocket.close();
    }
    
    public static void main(String args[]){
        try{
            Client client = new Client();
            client.run();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
