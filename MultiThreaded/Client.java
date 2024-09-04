import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public class RunnableImpl implements Runnable{
        public void run(){
            int port = 8010;
            try {
                InetAddress address = InetAddress.getByName("localhost");
                Socket clientSocket = new Socket(address, port);
                PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader fromSocket = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                toSocket.println("Hello from Client");
                String line = fromSocket.readLine();
                System.out.println("Response from the Socket is"+ line);
                toSocket.close();
                fromSocket.close();
                clientSocket.close();    
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    public static void main(String args[])
    {
        Client client = new Client();
        for(int i = 0; i < 100; i++)
        {
            try {
                Thread thread = new Thread(client.new RunnableImpl());
                thread.start();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
