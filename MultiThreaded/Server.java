import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {
 
    public Consumer<Socket> getConsumer(){//using functional interface to communicate with each client managed by thread 
        return (clientSocket)->{
            try{
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Hello from the server");
                toClient.close();
                clientSocket.close();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        };
    }
    public static void main (String args[]) throws IOException
    {
        int port = 8010;
        Server server = new Server();
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while(true)
        {
            try{
                System.out.println("Server is listening on port" + port);
                Socket acceptedConn = socket.accept();
                Thread thread = new Thread(()->server.getConsumer().accept(acceptedConn));
                thread.start();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
